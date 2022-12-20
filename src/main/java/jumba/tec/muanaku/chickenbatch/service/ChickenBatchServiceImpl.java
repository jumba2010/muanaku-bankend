package jumba.tec.muanaku.chickenbatch.service;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import jumba.tec.muanaku.chickenbatch.dto.ChickenBatchDTO;
import jumba.tec.muanaku.chickenbatch.repository.ChickenBatchRepository;
import jumba.tec.muanaku.feed.domain.FeedPrevision;
import jumba.tec.muanaku.feed.domain.FeedStatus;
import jumba.tec.muanaku.feed.repository.ChickenFeeRepository;
import jumba.tec.muanaku.feed.repository.FeedPrevisionRepository;
import jumba.tec.muanaku.utils.GeneralBusinessValidator;
import jumba.tec.muanaku.utils.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@RequiredArgsConstructor
@Service
public class ChickenBatchServiceImpl implements ChickenBatchService {

    public static final int KG_PER_BAG = 50;
    private final ChickenBatchRepository chickenBatchRepository;
    private final GeneralBusinessValidator generalBusinessValidator;
    private final ChickenFeeRepository chickenFeeRepository;

    private final FeedPrevisionRepository feedPrevisionRepository;


    @Override
    public void create(ChickenBatch chickenBatch) {
        generalBusinessValidator.validateChickenBatchForCreate(chickenBatch);
        chickenBatchRepository.save(chickenBatch);
    }

    @Override
    public void update(ChickenBatch chickenBatch) {
        generalBusinessValidator.validateChickenBatchForUpdate(chickenBatch);
        chickenBatchRepository.save(chickenBatch);
    }

    @Override
    public PageDto<ChickenBatchDTO> findByCompanyId(Long companyId, int limit, int offset) {
        Page<ChickenBatch> chickenBatches = chickenBatchRepository.findByCompanyId(companyId, PageRequest.of(offset, limit));
        List<ChickenBatchDTO> chickenBatchDTOList=chickenBatches.get().map(chickenBatch -> ChickenBatchDTO.fromChickenBatch(chickenBatch).get())
                .peek(chickenBatchDTO -> {
                    int bagsAllocatedSoFar=chickenFeeRepository.sumNumberOfBagsByBatchId(chickenBatchDTO.getBatchId(), FeedStatus.ACTIVE).getNumberOfBags();
                    double totalFeedAllocated=bagsAllocatedSoFar*KG_PER_BAG;
                    chickenBatchDTO.setRealConsumption(totalFeedAllocated);
                    List<FeedPrevision> feedPrevisions=feedPrevisionRepository.findByCompanyId(chickenBatchDTO.getCompanyId());
                    chickenBatchDTO.setExpectedConsumption(calculateConsumption(feedPrevisions,chickenBatchDTO.getEntranceDate(),chickenBatchDTO.getNrOfAliveChickens()));
                }).collect(Collectors.toList());
      return  new PageDto<>(chickenBatches.getTotalPages(),chickenBatches.getTotalElements(),
              chickenBatches.getNumberOfElements(),chickenBatchDTOList);
    }

    @Override
    public ChickenBatchDTO findById(Long id) {
        ChickenBatch chickenBatch = chickenBatchRepository.fetchById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No ChickenBatch found by the given Id"));
        return ChickenBatchDTO.fromChickenBatch(chickenBatch)
                .stream().peek(chickenBatchDTO -> {
                    int bagsAllocatedSoFar=chickenFeeRepository.sumNumberOfBagsByBatchId(chickenBatch.getId(), FeedStatus.ACTIVE).getNumberOfBags();
                    double totalFeedAllocated=bagsAllocatedSoFar*KG_PER_BAG;
                    chickenBatchDTO.setRealConsumption(totalFeedAllocated);
                    List<FeedPrevision> feedPrevisions=feedPrevisionRepository.findByCompanyId(chickenBatchDTO.getCompanyId());
                    chickenBatchDTO.setExpectedConsumption(calculateConsumption(feedPrevisions,chickenBatchDTO.getEntranceDate(),chickenBatchDTO.getNrOfAliveChickens()));
                }).findFirst().get();


    }

    private double calculateConsumption(final List<FeedPrevision> feedPrevisions,LocalDate entranceDate,int aliveQuantity){
        double totalConsumptionInKg=0.0;
        Collections.sort(feedPrevisions, Comparator.comparingInt(FeedPrevision::getOfDay));
        long daysPassed=DAYS.between(LocalDate.now(),entranceDate);
        List<FeedPrevision> feedPrevisionSoFar=feedPrevisions.stream().filter(feedPrevision ->feedPrevision.getOfDay()<=daysPassed )
                .collect(Collectors.toList());
        for (FeedPrevision feedPrevision:feedPrevisionSoFar) {
            totalConsumptionInKg+=feedPrevision
                    .getDailyConsumption()*aliveQuantity;
        }

        return totalConsumptionInKg;
    }
}
