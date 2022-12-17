package jumba.tec.muanaku.chickenbatch.resource;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import jumba.tec.muanaku.chickenbatch.service.ChickenBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("chicken-batch")
public class ChickenBatchResource {
private final ChickenBatchService chickenBatchService;

    @PostMapping
    public void createChickenBatch(@RequestBody @Valid ChickenBatch chickenBatch) {
        chickenBatchService.create(chickenBatch);
    }

    @PutMapping
    public void updateChickenBatch(@RequestBody @Valid ChickenBatch chickenBatch) {
        chickenBatchService.update(chickenBatch);
    }

    @GetMapping("/company/{companyId}")
    public List<ChickenBatch> findByCompanyId(@PathVariable("companyId") Long companyId) {
        return chickenBatchService.findByCompanyId(companyId);
    }

    @GetMapping("/{id}")
    public ChickenBatch findById(@PathVariable("id") Long id) {
        return chickenBatchService.findById(id);
    }
}
