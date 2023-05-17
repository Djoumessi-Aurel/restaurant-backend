package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.Serveur;

@Repository
public interface ServeurRepository extends CrudRepository<Serveur, Integer> {
    
}
