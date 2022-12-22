package jumba.tec.muanaku.vaccine.domain;

import jumba.tec.muanaku.user.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@Entity
public class ChickenBatchVaccine {

    @Id
    private Long Id;

    @JoinColumn(name="vaccine_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Vaccine vaccine;

    @NotNull
    @Column(name="vaccine_id",nullable = false)
    private Long vaccineID;

    @Column(name="created_at",nullable = false)
    private Instant createdAt=Instant.now();

    @Column(name="notes")
    private String notes;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name="expected_start_date",nullable = false)
    private LocalDate expectedStartDate;

    @NotNull
    @Column(name="expected_end_date",nullable = false)
    private LocalDate expectedEndDate;

    @NotNull
    @Column(name="company_id",nullable = false)
    private Long companyId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Company company;

}
