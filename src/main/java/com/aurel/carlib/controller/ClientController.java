package com.aurel.carlib.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aurel.carlib.model.Client;
import com.aurel.carlib.repository.ClientRepository;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    
    @Autowired
	private ClientRepository repo;
	
	@GetMapping
    public ResponseEntity<List<Client>> getAllClients(@RequestParam(required = false) String nom) {
    try {
        List<Client> clients = new ArrayList<Client>();

        if (nom == null)
        repo.findAll().forEach(clients::add);
        else
        repo.findByNom(nom).forEach(clients::add);

        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") int id) {
        Optional<Client> client = repo.findById(id);

        if (client.isPresent()) {
        return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
        Client _client = repo.save(new Client(client.getNom()));
        return new ResponseEntity<>(_client, HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
