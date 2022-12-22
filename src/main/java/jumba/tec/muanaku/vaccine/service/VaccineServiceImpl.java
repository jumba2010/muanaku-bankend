package jumba.tec.muanaku.vaccine.service;

import jumba.tec.muanaku.chickenbatch.repository.ChickenBatchRepository;
import jumba.tec.muanaku.vaccine.domain.Vaccine;
import jumba.tec.muanaku.vaccine.repository.ChickenBatchVaccineRepository;
import jumba.tec.muanaku.vaccine.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineServiceImpl implements  VaccineService{

    private final VaccineRepository vaccineRepository;

    private ChickenBatchVaccineRepository chickenBatchVaccineRepository;
    @Override
    public void createVaccine(Vaccine vaccine) {

    }

    @Override
    public void updateVaccine(Vaccine vaccine) {

    }

    @Override
    public Vaccine getVaccineById(Long id) {
        return null;
    }

    @Override
    public List<Vaccine> getVaccineByCompanyId(Long id) {
        return null;
    }
}
