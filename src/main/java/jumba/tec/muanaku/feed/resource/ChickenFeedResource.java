package jumba.tec.muanaku.feed.resource;

import jumba.tec.muanaku.feed.domain.ChickenFeed;
import jumba.tec.muanaku.feed.domain.ReplacementDateDTO;
import jumba.tec.muanaku.feed.dto.ChickenFeedAllocationRequest;
import jumba.tec.muanaku.feed.service.ChickenFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chicken-feed")
public class ChickenFeedResource {
    private final ChickenFeedService chickenFeedService;

    @PostMapping("/allocation")
    public void allocateChickenFeed(@Valid @RequestBody ChickenFeedAllocationRequest chickenFeedAllocationRequest){
        chickenFeedService.allocateBags(chickenFeedAllocationRequest);
    }

    @PutMapping("/allocation/undo/{chickenFeedId}"
)    public void updateChickenFeed(@PathVariable("chickenFeedId") Long chickenFeedId){
        chickenFeedService.undoChickenFeedAllocation(chickenFeedId);
    }

    @GetMapping("/batch/{id}")
    public List<ChickenFeed> findChickenFeedByBatchId(@PathVariable("id") Long  batchId){
        return chickenFeedService.findChickenFeedByBatchId(batchId);
    }

    @GetMapping("/{id}")
    public ChickenFeed findChickenFeedById(@PathVariable("id") Long  id){
        return chickenFeedService.findChickenFeedById(id);
    }


    @GetMapping("/replacement-date")
    public LocalDate getReplacementDateByBatchId(@RequestParam ChickenFeedAllocationRequest chickenFeedAllocationRequest){
        return chickenFeedService.getExpectedReplacementDate(chickenFeedAllocationRequest.getBatchId(),chickenFeedAllocationRequest.getNumberOfBags(),chickenFeedAllocationRequest.getCompanyId());
    }

    @GetMapping("/count-days/{batchId}")
    public Long getCountDaysByBatchId(@RequestParam("batchId") Long batchId){
        return chickenFeedService.getCountDaysByBatchId(batchId);
    }


}
