package jumba.tec.muanaku.feed.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ChickenFeedAllocationRequest {
    @NotNull
    private Long batchId;

    @NotNull
    @Positive
    private int numberOfBags;

    @NotNull
    private Long companyId;
}
