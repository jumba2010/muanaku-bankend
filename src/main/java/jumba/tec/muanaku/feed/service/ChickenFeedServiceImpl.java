package jumba.tec.muanaku.feed.service;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import jumba.tec.muanaku.chickenbatch.repository.ChickenBatchRepository;
import jumba.tec.muanaku.feed.domain.ChickenFeed;
import jumba.tec.muanaku.feed.domain.FeedPrevision;
import jumba.tec.muanaku.feed.domain.FeedStatus;
import jumba.tec.muanaku.feed.dto.ChickenFeedAllocationRequest;
import jumba.tec.muanaku.feed.repository.ChickenFeeRepository;
import jumba.tec.muanaku.feed.repository.FeedPrevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class ChickenFeedServiceImpl implements  ChickenFeedService{

    public static final int KG_PER_BAG = 50;
    private final  ChickenFeeRepository chickenFeeRepository;

    private final ChickenBatchRepository chickenBatchRepository;

    private final FeedPrevisionRepository feedPrevisionRepository;

    @Override
    public LocalDate  getExpectedReplacementDate(final Long batchId, final int numberOfBags,Long companyId) {
        ChickenBatch chickenBatch=chickenBatchRepository.findById(batchId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Chicken Batch not found by the given batchId"));
        int bagsAllocatedSoFar=chickenFeeRepository.sumNumberOfBagsByBatchId(batchId,FeedStatus.ACTIVE).getNumberOfBags();
        double totalFeedAllocated=(bagsAllocatedSoFar+numberOfBags)*KG_PER_BAG;
        int numberOfDays=0;
        double totalConsumptionInKg=0.0;
        List<FeedPrevision> feedPrevisions=feedPrevisionRepository.findByCompanyId(companyId);
        Collections.sort(feedPrevisions,Comparator.comparingInt(FeedPrevision::getOfDay));
        for (int i=0;totalConsumptionInKg<=totalFeedAllocated;i++) {
            totalConsumptionInKg+=feedPrevisions.get(i)
                    .getDailyConsumption()*chickenBatch.getAliveQuantity();
            numberOfDays++;
        }

       return chickenBatch.getEntranceDate().plusDays(numberOfDays);
    }

    @Override
    public List<ChickenFeed> findChickenFeedByBatchId(Long batchId) {
        return chickenFeeRepository.findChickenFeedByBatchIdAndFeedStatus(batchId, FeedStatus.ACTIVE);
    }

    @Override
    public ChickenFeed findChickenFeedById(Long id) {
        return chickenFeeRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No chicken feed found by the given Id"));
    }

    @Override
    public Long getCountDaysByBatchId(Long batchId) {
        ChickenBatch chickenBatch=chickenBatchRepository.findById(batchId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Chicken Batch not found by the given batchId"));
      return DAYS.between(LocalDate.now(),chickenBatch.getEntranceDate());
    }

    @Override
    public void allocateBags(ChickenFeedAllocationRequest chickenFeedAllocationRequest) {
        LocalDate expectedReplacementDate=getExpectedReplacementDate(chickenFeedAllocationRequest.getBatchId(),chickenFeedAllocationRequest.getNumberOfBags(),chickenFeedAllocationRequest.getCompanyId());
        ChickenFeed chickenFeed=ChickenFeed.builder()
                .allocationDate(LocalDate.now())
                .batchId(chickenFeedAllocationRequest.getBatchId())
                .createdAt(Instant.now())
                .companyId(chickenFeedAllocationRequest.getCompanyId())
                .numberOfBags(chickenFeedAllocationRequest.getNumberOfBags())
                .status(FeedStatus.ACTIVE)
                .expectedReplacementDate(expectedReplacementDate)
                .build();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ChickenFeed>> violations = validator.validate( chickenFeed);
       if(!violations.isEmpty()){
           throw  new ConstraintViolationException(violations);
       }

        chickenFeeRepository.save(chickenFeed);
    }

    @Override
    public void undoChickenFeedAllocation(Long chickenFeedId) {
        ChickenFeed chickenFeed=chickenFeeRepository.findById(chickenFeedId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Chicken Feed not found by the given Id"));
        chickenFeed.setStatus(FeedStatus.INVALID);
        chickenFeeRepository.save(chickenFeed);
    }
}
