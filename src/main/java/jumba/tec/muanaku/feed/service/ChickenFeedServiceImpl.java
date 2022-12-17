package jumba.tec.muanaku.feed.service;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import jumba.tec.muanaku.chickenbatch.repository.ChickenBatchRepository;
import jumba.tec.muanaku.feed.domain.ChickenFeed;
import jumba.tec.muanaku.feed.repository.ChickenFeeRepository;
import jumba.tec.muanaku.feed.repository.FeedPrevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class ChickenFeedServiceImpl implements  ChickenFeedService{

    private final  ChickenFeeRepository chickenFeeRepository;

    private final ChickenBatchRepository chickenBatchRepository;

    private final FeedPrevisionRepository feedPrevisionRepository;

    @Override
    public void createChickenFeed(ChickenFeed chickenFeed) {
        LocalDate expectedReplacementDate=getExpectedReplacementDate(chickenFeed);
        chickenFeed.setExpectedReplacementDate(expectedReplacementDate);
        chickenFeeRepository.save(chickenFeed);
    }

    private LocalDate getExpectedReplacementDate(ChickenFeed chickenFeed) {
        ChickenBatch chickenBatch=chickenBatchRepository.findById(chickenFeed.getBatchId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Chicken Batch not found by the given batchId"));
        int chickenQuantity=chickenBatch.getAliveQuantity();
        long daysPassed=DAYS.between(LocalDate.now(),chickenBatch.getEntranceDate());
        int bagsAllocatedSoFar=chickenFeeRepository.sumNumberOfBagsByBatchId(chickenFeed.getBatchId()).getNumberOfBags();
        int totalBags=bagsAllocatedSoFar+chickenFeed.getNumberOfBags();
        return null;
    }

    @Override
    public void updateChickenFeed(ChickenFeed chickenFeed) {
        chickenFeeRepository.save(chickenFeed);
    }

    @Override
    public List<ChickenFeed> findChickenFeedByBatchId(Long batchId) {
        return chickenFeeRepository.findChickenFeedByBatchId(batchId);
    }

    @Override
    public ChickenFeed findChickenFeedById(Long id) {
        return chickenFeeRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No chicken feed found by the given Id"));
    }
}
