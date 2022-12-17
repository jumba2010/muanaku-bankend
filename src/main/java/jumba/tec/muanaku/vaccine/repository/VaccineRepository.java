package jumba.tec.muanaku.vaccine.repository;

import jumba.tec.muanaku.vaccine.domain.ChickenBatchVaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<ChickenBatchVaccine,Long> {
}
