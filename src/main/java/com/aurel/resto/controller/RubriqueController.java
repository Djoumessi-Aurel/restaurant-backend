package com.aurel.resto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurel.resto.model.Rubrique;
import com.aurel.resto.repository.RubriqueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/rubrique")
public class RubriqueController {
    
    @Autowired
	private RubriqueRepository repo;
	
	@GetMapping
    public ResponseEntity<List<Rubrique>> getAllRubriques(@RequestParam(required = false) String nom) {
    try {
        List<Rubrique> rubriques = new ArrayList<Rubrique>();

        if (nom == null)
        repo.findAll().forEach(rubriques::add);
        else
        repo.findByNom(nom).forEach(rubriques::add);

        if (rubriques.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rubriques, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rubrique> getRubriqueById(@PathVariable("id") int id) {
        Optional<Rubrique> rubrique = repo.findById(id);

        if (rubrique.isPresent()) {
        return new ResponseEntity<>(rubrique.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Rubrique> createRubrique(@RequestBody Rubrique rubrique) {
        try {
        Rubrique _rubrique = repo.save(new Rubrique(rubrique.getNom(), rubrique.getDescription()));
        return new ResponseEntity<>(_rubrique, HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
