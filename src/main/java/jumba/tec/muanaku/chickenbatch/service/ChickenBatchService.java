package jumba.tec.muanaku.chickenbatch.service;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;

import java.util.List;

public interface ChickenBatchService {

    void create(ChickenBatch chickenBatch);

    void update(ChickenBatch chickenBatch);

    List<ChickenBatch> findByCompanyId(final Long companyId);

    ChickenBatch findById(Long id);
}
