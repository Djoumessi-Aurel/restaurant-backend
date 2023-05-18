package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.carlib.model.ModePaiement;

public interface ModePaiementRepository extends CrudRepository<ModePaiement, Integer> {

    public Iterable<ModePaiement> findByNom(String name);
    
}