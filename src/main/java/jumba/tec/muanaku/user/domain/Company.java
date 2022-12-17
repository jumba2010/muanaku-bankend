package jumba.tec.muanaku.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Company {

    @Id
    private Long Id;

    private String name;

    private String logo;

    private Instant createdAt;

}
