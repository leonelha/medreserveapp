package clientsArchitecture.domain.services;

import clientsArchitecture.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    List<Client> getAllClients();

    Optional<Client> getClientById(Long id);

    Client createClient(Client client);

    Client updateClient(Long id, Client client);

    void deleteClient(Long id);
}
