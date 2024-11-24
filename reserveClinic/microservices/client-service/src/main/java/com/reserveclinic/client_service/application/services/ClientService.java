package com.reserveclinic.client_service.application.services;

import com.reserveclinic.client_service.domain.model.Client;
import com.reserveclinic.client_service.domain.repositories.IClientRepository;
import com.reserveclinic.client_service.domain.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    @Autowired
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client createClient(Client clientRequest) {
         var client = new Client.ClientBuilder()
                 .setFirstName(clientRequest.getFirstName())
                 .setLastName(clientRequest.getLastName())
                 .setUserName(clientRequest.getUserName())
                 .setEmail(clientRequest.getEmail())
                 .setPhone(clientRequest.getPhone())
                 .setAddress(clientRequest.getAddress())
                 .setPassword(clientRequest.getPassword())
                 .build();
         return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        client.setId(id);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
