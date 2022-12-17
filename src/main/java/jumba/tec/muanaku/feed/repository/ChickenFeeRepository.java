package jumba.tec.muanaku.feed.repository;

import jumba.tec.muanaku.feed.domain.ChickenFeed;
import jumba.tec.muanaku.vaccine.domain.ChickenBatchVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChickenFeeRepository extends JpaRepository<ChickenFeed,Long> {
    List<ChickenFeed> findChickenFeedByBatchId(Long batchId);

    @Query(value = "Select sum(number_of_bags) as numberOfBags from chicken_feed where batch_id = :batchId" ,nativeQuery = true)
    NumberOfBags sumNumberOfBagsByBatchId(@Param("batchId") Long batchId);
}
