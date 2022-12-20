package jumba.tec.muanaku.feed.domain;

import jumba.tec.muanaku.user.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.Instant;

@Data
@AllArgsConstructor
@Entity
public class FeedPrevision {
    @Id
    private Long Id;

    @Column(name="created_at",nullable = false)
    private Instant createdAt;

    @Column(name="updated_at",nullable = false)
    private Instant updatedAt;

    @Column(name="of_day",nullable = false)
    private int ofDay;

    //The dailyConsumption will be in Kg
    @Column(name="daily_consumption",nullable = false)
    private double dailyConsumption;

    @Column(name="company_id",nullable = false)
    private Long companyId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Company company;

}
