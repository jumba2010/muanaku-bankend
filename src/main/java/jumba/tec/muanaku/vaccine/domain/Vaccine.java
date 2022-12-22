package jumba.tec.muanaku.vaccine.domain;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class Vaccine {

    @Id
    private Long Id;

    @Column(name="created_at",nullable = false)
    private Instant createdAt=Instant.now();

    @NotBlank
    @Max(value = 100)
    @Column(name="name",nullable = false)
    private String name;

    @Min(value = 1)
    @Column(name="number_of_doses",nullable = false)
    private int numberOfDoses;

    @Type(type = "json")
    @NotEmpty
    @Column(columnDefinition = "jsonb")
    private List<Dose> doses;

    @NotNull
    @Column(name="company_id",nullable = false)
    private Long companyId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Company company;

}
