package jumba.tec.muanaku.sales.domain;

import jumba.tec.muanaku.user.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
public class ChickenBatchCost{
    @Id
    private Long Id;

    @Column(name="created_at",nullable = false)
    private Instant createdAt;

    @Column(name = "date",nullable = false)
    private LocalDate date;

    @Column(name="amount",nullable = false)
    private BigDecimal amount;

    @Column(name = "note",nullable = false)
    private String note;

    @Column(name="company_id",nullable = false)
    private Long companyId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Company company;
}
