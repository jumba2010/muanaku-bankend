package jumba.tec.muanaku.chickenbatch.service;

import jumba.tec.muanaku.utils.GeneralBusinessValidator;
import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import jumba.tec.muanaku.chickenbatch.repository.ChickenBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChickenBatchServiceImpl  implements  ChickenBatchService{

    private final ChickenBatchRepository chickenBatchRepository;

    private final GeneralBusinessValidator generalBusinessValidator;
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
    public List<ChickenBatch> findByCompanyId(Long companyId) {
        return chickenBatchRepository.findByCompanyId(companyId);
    }

    @Override
    public ChickenBatch findById(Long id) {
        return chickenBatchRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No ChickenBatch found by the given Id"));
    }
}
