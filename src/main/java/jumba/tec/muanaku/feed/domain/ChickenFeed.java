package jumba.tec.muanaku.feed.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jumba.tec.muanaku.chickenbatch.domain.ChickenBatch;
import jumba.tec.muanaku.user.domain.Company;
import jumba.tec.muanaku.vaccine.domain.Dose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name="chicken_feed")
@Builder
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class ChickenFeed {

    @Id
    private Long Id;

    @Column(name="created_at",nullable = false)
    private Instant createdAt;

    @JoinColumn(name = "batch_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ChickenBatch chickenBatch;

    @Column(name = "batch_id",nullable = false)
    private Long batchId;

    @Column(name="allocation_date",nullable = false)
    private LocalDate allocationDate;

    @Column(name="number_of_bags",nullable = false)
    private int numberOfBags;

    @Column(name="expected_replacement_date",nullable = false)
    private LocalDate expectedReplacementDate;

    @Column(name = "feed_status")
    @Enumerated(EnumType.STRING)
    private FeedStatus status;

    @Column(name="company_id",nullable = false)
    private Long companyId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Company company;


}
