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

import com.aurel.carlib.model.ModePaiement;
import com.aurel.carlib.repository.ModePaiementRepository;

@RestController
@RequestMapping("/api/modePaiement")
public class ModePaiementController {
    
    @Autowired
	private ModePaiementRepository repo;
	
	@GetMapping
    public ResponseEntity<List<ModePaiement>> getAllModePaiements(@RequestParam(required = false) String nom) {
    try {
        List<ModePaiement> modePaiements = new ArrayList<ModePaiement>();

        if (nom == null)
        repo.findAll().forEach(modePaiements::add);
        else
        repo.findByNom(nom).forEach(modePaiements::add);

        if (modePaiements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(modePaiements, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModePaiement> getModePaiementById(@PathVariable("id") int id) {
        Optional<ModePaiement> modePaiement = repo.findById(id);

        if (modePaiement.isPresent()) {
        return new ResponseEntity<>(modePaiement.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ModePaiement> createModePaiement(@RequestBody ModePaiement modePaiement) {
        try {
        ModePaiement _modePaiement = repo.save(new ModePaiement(modePaiement.getNom(), modePaiement.getDescription()));
        return new ResponseEntity<>(_modePaiement, HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
