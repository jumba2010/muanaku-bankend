package jumba.tec.muanaku.client.service;

import jumba.tec.muanaku.client.domain.Client;

import java.util.List;

public interface ClientService {
    void createClient(Client client);

    void updateClient(Client client);

    List<Client> findByCompanyId(Long companyId);

    Client findById(Long id);
}
