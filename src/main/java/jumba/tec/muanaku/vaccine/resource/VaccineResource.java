package jumba.tec.muanaku.vaccine.resource;

import jumba.tec.muanaku.vaccine.domain.Vaccine;
import jumba.tec.muanaku.vaccine.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vaccine")
public class VaccineResource {
    private final VaccineService vaccineService;

    @PostMapping
    public void createVaccine(@RequestBody @Valid Vaccine vaccine){
        vaccineService.createVaccine(vaccine);
    }

    @PutMapping
    public void updateVaccine(@RequestBody @Valid Vaccine vaccine){
        vaccineService.updateVaccine(vaccine);
    }

    @GetMapping("/{id}")
    public Vaccine getVaccineById(@PathVariable("id") Long id){
        return vaccineService.getVaccineById(id);
    }

    @GetMapping("/companyId/{companyId}")
    public List<Vaccine> getVaccineByCompanyId(@PathVariable("id") Long id){
        return vaccineService.getVaccineByCompanyId(id);
    }

}
