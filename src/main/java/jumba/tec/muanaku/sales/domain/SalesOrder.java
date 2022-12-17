package jumba.tec.muanaku.sales.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jumba.tec.muanaku.user.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class SalesOrder {

    @Id
    private Long Id;

    @Column(name="created_at",nullable = false)
    private Instant createdAt;

    @Column(name="total",nullable = false)
    private BigDecimal total;

    @Column(name="paid",nullable = false)
    private boolean paid;

    @Column(name="delivered",nullable = false)
    private boolean delivered;

    @Column(columnDefinition = "text[]",name="notes")
    @Type(type = "string-array")
    private String[] notes;

    @Column(name="expected_payment_date")
    private LocalDate expectedPaymentDate;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    @Builder.Default
    private List<SalesItem> items;

    @Column(name="company_id",nullable = false)
    private Long companyId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Company company;
}
