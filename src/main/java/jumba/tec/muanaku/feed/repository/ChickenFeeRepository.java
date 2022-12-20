package jumba.tec.muanaku.feed.repository;

import jumba.tec.muanaku.feed.domain.ChickenFeed;
import jumba.tec.muanaku.feed.domain.FeedStatus;
import jumba.tec.muanaku.vaccine.domain.ChickenBatchVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChickenFeeRepository extends JpaRepository<ChickenFeed,Long> {

    @Query(value = "Select sum(number_of_bags) as numberOfBags from chicken_feed where batch_id = :batchId and feed_status =:status" ,nativeQuery = true)
    NumberOfBags sumNumberOfBagsByBatchId(@Param("batchId") Long batchId,@Param("status")FeedStatus status);

    List<ChickenFeed> findChickenFeedByBatchIdAndFeedStatus(Long batchId, FeedStatus status);
}
