package jumba.tec.muanaku.feed.service;

import jumba.tec.muanaku.feed.domain.ChickenFeed;

import java.util.List;

public interface ChickenFeedService {
    void createChickenFeed(ChickenFeed chickenFeed);

    void updateChickenFeed(ChickenFeed chickenFeed);

    List<ChickenFeed> findChickenFeedByBatchId(Long batchId);

    ChickenFeed findChickenFeedById(Long id);
}
