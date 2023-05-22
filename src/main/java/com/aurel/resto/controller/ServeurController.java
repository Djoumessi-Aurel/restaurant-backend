package com.aurel.resto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurel.resto.model.Serveur;
import com.aurel.resto.repository.ServeurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/serveur")
public class ServeurController {
    
    @Autowired
	private ServeurRepository repo;
	
	@GetMapping
    public ResponseEntity<List<Serveur>> getAllServeurs(@RequestParam(required = false) String nom) {
    try {
        List<Serveur> serveurs = new ArrayList<Serveur>();

        if (nom == null)
        repo.findAll().forEach(serveurs::add);
        else
        repo.findByNom(nom).forEach(serveurs::add);

        if (serveurs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(serveurs, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serveur> getServeurById(@PathVariable("id") int id) {
        Optional<Serveur> serveur = repo.findById(id);

        if (serveur.isPresent()) {
        return new ResponseEntity<>(serveur.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Serveur> createServeur(@RequestBody Serveur serveur) {
        try {
        Serveur _serveur = repo.save(new Serveur(serveur.getNom(), serveur.getIdentifiant(), serveur.getMotdepasse()));
        return new ResponseEntity<>(_serveur, HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
