package com.reserveclinic.client_service.presentation.controllers;

import com.reserveclinic.client_service.domain.model.Client;
import com.reserveclinic.client_service.domain.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final IClientService clientService;
    private final WebClient.Builder webClient;

    @Autowired
    public ClientController(IClientService clientService, WebClient.Builder webClient) {
        this.clientService = clientService;
        this.webClient = webClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client clientRequest) {
        return clientService.createClient(clientRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
