package jumba.tec.muanaku.vaccine.service;

import jumba.tec.muanaku.vaccine.domain.Vaccine;

import java.util.List;

public interface VaccineService {
    void createVaccine(Vaccine vaccine);

    void updateVaccine(Vaccine vaccine);

    Vaccine getVaccineById(Long id);

    List<Vaccine> getVaccineByCompanyId(Long id);
}
