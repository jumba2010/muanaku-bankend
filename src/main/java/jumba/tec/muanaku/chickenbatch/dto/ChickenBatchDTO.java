package jumba.tec.muanaku.chickenbatch.dto;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import jumba.tec.muanaku.feed.domain.ChickenFeed;
import jumba.tec.muanaku.feed.domain.Issue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChickenBatchDTO {
    private Long batchId;
    private Long companyId;
    private int nrOfAliveChickens;
    private int nrOfDeadChickens;
    private LocalDate entranceDate;
    private LocalDate exitDate;
    private LocalDate expectedExitDate;
    private double expectedConsumption;
    private double realConsumption;
    private BigDecimal pluckedPrice;
    private BigDecimal unPluckedPrice;
    private List<Issue> issues;
    private List<ChickenFeed> chickenFeed;

    public static Optional<ChickenBatchDTO> fromChickenBatch(ChickenBatch chickenBatch){
        return Optional.of(ChickenBatchDTO.builder()
                .batchId(chickenBatch.getId())
                .chickenFeed(chickenBatch.getChickenFeed())
                .nrOfAliveChickens(chickenBatch.getAliveQuantity())
                .nrOfDeadChickens(chickenBatch.getDeathQuantity())
                .companyId(chickenBatch.getCompanyId())
                .entranceDate(chickenBatch.getEntranceDate())
                .exitDate(chickenBatch.getExitDate())
                .expectedExitDate(chickenBatch.getExpectedExitDate())
                .pluckedPrice(chickenBatch.getPluckedPrice())
                .unPluckedPrice(chickenBatch.getUnPluckedPrice())
                .build());
    }

}
