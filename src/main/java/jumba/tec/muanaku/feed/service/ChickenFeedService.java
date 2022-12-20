package jumba.tec.muanaku.feed.service;

import jumba.tec.muanaku.feed.domain.ChickenFeed;
import jumba.tec.muanaku.feed.dto.ChickenFeedAllocationRequest;

import java.time.LocalDate;
import java.util.List;

public interface ChickenFeedService {

    LocalDate getExpectedReplacementDate(Long batchId, int numberOfBags,Long companyId);

    List<ChickenFeed> findChickenFeedByBatchId(Long batchId);

    ChickenFeed findChickenFeedById(Long id);

    Long getCountDaysByBatchId(Long batchId);

    void allocateBags(ChickenFeedAllocationRequest chickenFeedAllocationRequest);

    void undoChickenFeedAllocation(Long chickenFeedId);
}
