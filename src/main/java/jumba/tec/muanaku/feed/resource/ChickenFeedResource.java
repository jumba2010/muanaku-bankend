package jumba.tec.muanaku.feed.resource;

import jumba.tec.muanaku.feed.domain.ChickenFeed;
import jumba.tec.muanaku.feed.service.ChickenFeedService;
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
@RequestMapping("/chicken-feed")
public class ChickenFeedResource {
    private final ChickenFeedService chickenFeedService;

    @PostMapping
    public void createChickenFeed(@Valid @RequestBody ChickenFeed chickenFeed){
        chickenFeedService.createChickenFeed(chickenFeed);
    }

    @PutMapping
    public void updateChickenFeed(@Valid @RequestBody ChickenFeed chickenFeed){
        chickenFeedService.updateChickenFeed(chickenFeed);
    }

    @GetMapping("/batch/{id}")
    public List<ChickenFeed> findChickenFeedByBatchId(@PathVariable("id") Long  batchId){
        return chickenFeedService.findChickenFeedByBatchId(batchId);
    }

    @GetMapping("/{id}")
    public ChickenFeed findChickenFeedById(@PathVariable("id") Long  id){
        return chickenFeedService.findChickenFeedById(id);
    }


}
