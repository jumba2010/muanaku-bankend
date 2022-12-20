package jumba.tec.muanaku.feed.domain;

import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "t_issue")
public class Issue {

    @Id
    private Long id;

    @Column(name = "created_at",nullable = false)
    private Instant createdAt=Instant.now();

    @Column(name = "description",nullable = false)
    private String description;

    @JoinColumn(name = "batch_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ChickenBatch chickenBatch;

    @Column(name = "batch_id",nullable = false)
    private Long batchId;


    @Column(name = "real_quantity")
    private double realQuantity;

    @Column(name = "expected_quantity")
    private double expectedQuantity;

    @Column(name = "real_replacement_date")
    private LocalDate realReplacementDate;

    @Column(name = "expected_replacement_date")
    private LocalDate expectedReplacementDate;

    @Column(name = "real_exit_date")
    private LocalDate realExitDate;

    @Column(name = "expected_exit_date")
    private LocalDate expectedExitDate;

}
