package jumba.tec.muanaku.vaccine.repository;

import jumba.tec.muanaku.vaccine.domain.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine,Long> {
}
