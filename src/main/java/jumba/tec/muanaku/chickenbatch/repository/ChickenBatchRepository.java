package jumba.tec.muanaku.chickenbatch.repository;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChickenBatchRepository extends PagingAndSortingRepository<ChickenBatch,Long> {

    @Query("select cb from ChickenBatch cb inner join fetch cb.chickenFeed cf inner join fetch cb.issues i")
    Page<ChickenBatch> findByCompanyId(Long companyId, Pageable pageable);

    @Query("select cb from ChickenBatch cb inner join fetch cb.chickenFeed cf inner join fetch cb.issues i where cb.id=:id")
    Optional<ChickenBatch> fetchById(@Param("id") Long id);
}
