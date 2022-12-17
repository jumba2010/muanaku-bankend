package jumba.tec.muanaku.client.resource;

import jumba.tec.muanaku.client.domain.Client;
import jumba.tec.muanaku.client.service.ClientService;
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
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @PostMapping
    public void createClient(@RequestBody @Valid Client client){
        clientService.createClient(client);
    }

    @PutMapping
    public void updateClient(@RequestBody @Valid Client client){
        clientService.updateClient(client);
    }

    @GetMapping("/company/{companyId}")
    public List<Client> findByCompanyId(@PathVariable("companyId") Long companyId){
       return  clientService.findByCompanyId(companyId);
    }


    @GetMapping("/{id}")
    public Client  findById(@PathVariable("id") Long id){
        return clientService.findById(id);
    }

}
