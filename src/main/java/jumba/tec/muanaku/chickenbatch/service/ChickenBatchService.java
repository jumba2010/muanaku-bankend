package jumba.tec.muanaku.chickenbatch.service;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import jumba.tec.muanaku.chickenbatch.dto.ChickenBatchDTO;
import jumba.tec.muanaku.utils.PageDto;

import java.util.List;

public interface ChickenBatchService {

    void create(ChickenBatch chickenBatch);

    void update(ChickenBatch chickenBatch);

    PageDto<ChickenBatchDTO> findByCompanyId(final Long companyId,int limit, int offset);

    ChickenBatchDTO findById(Long id);
}
