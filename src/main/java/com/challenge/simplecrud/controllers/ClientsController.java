package com.challenge.simplecrud.controllers;

import ch.qos.logback.core.net.server.Client;
import com.challenge.simplecrud.domain.clients.Clients;
import com.challenge.simplecrud.domain.clients.ClientsRepository;
import com.challenge.simplecrud.domain.clients.RequestClientsDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsRepository clientsRepository;

    @GetMapping
    public ResponseEntity getAllClients(){
        return ResponseEntity.ok(clientsRepository.findAll());
    }

    @PostMapping
    public ResponseEntity registerClient(@RequestBody @Valid RequestClientsDTO data){
        Clients newClient = new Clients(data);

        clientsRepository.save(newClient);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateClient(@RequestBody @Valid RequestClientsDTO data){
        Optional<Clients> optionalClient = clientsRepository.findById(data.id());
        if(optionalClient.isPresent()){
            Clients clients = optionalClient.get();
            clients.setName(data.name());
            clients.setEmail(data.email());
            clients.setPhone(data.phone());
            return ResponseEntity.ok(clients);
        }
        throw new EntityNotFoundException();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Integer id){
        clientsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
