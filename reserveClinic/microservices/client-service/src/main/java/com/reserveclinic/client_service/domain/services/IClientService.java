package com.reserveclinic.client_service.domain.services;

import com.reserveclinic.client_service.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    List<Client> getAllClients();

    Optional<Client> getClientById(Long id);

    Client createClient(Client client);

    Client updateClient(Long id, Client client);

    void deleteClient(Long id);
}
