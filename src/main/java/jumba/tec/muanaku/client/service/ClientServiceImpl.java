package jumba.tec.muanaku.client.service;

import jumba.tec.muanaku.client.domain.Client;
import jumba.tec.muanaku.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findByCompanyId(Long companyId) {
        return clientRepository.findByCompanyId(companyId);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No client found by the given Id"));
    }
}
