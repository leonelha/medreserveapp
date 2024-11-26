package com.reserveclinic.client_service.presentation.controllers;

import com.reserveclinic.client_service.domain.model.Client;
import com.reserveclinic.client_service.domain.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client clientRequest) {
        return clientService.createClient(clientRequest);
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody Client clientRequest) {
        return ResponseEntity.ok(clientService.updateClient(clientRequest.getId(), clientRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    ////
    @GetMapping("/search/{idAppointment}")
    public ResponseEntity<?> getClientByAppointmentId(@PathVariable Long idAppointment) {
        return ResponseEntity.ok(clientService.getClientByIdAppointment(idAppointment));
    }

}
