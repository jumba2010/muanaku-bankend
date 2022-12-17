package jumba.tec.muanaku.chickenbatch.repository;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChickenBatchRepository extends JpaRepository<ChickenBatch,Long> {
    List<ChickenBatch> findByCompanyId(Long companyId);
}
