package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.ModePaiement;

@Repository
public interface ModePaiementRepository extends CrudRepository<ModePaiement, Integer> {

    public Iterable<ModePaiement> findByNom(String name);
    
}