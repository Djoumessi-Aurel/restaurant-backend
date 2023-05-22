package com.aurel.resto.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.resto.model.ModePaiement;

public interface ModePaiementRepository extends CrudRepository<ModePaiement, Integer> {

    public Iterable<ModePaiement> findByNom(String name);
    
}