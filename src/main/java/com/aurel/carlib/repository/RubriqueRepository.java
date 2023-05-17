package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.Rubrique;

@Repository
public interface RubriqueRepository extends CrudRepository<Rubrique, Integer> {

    public Iterable<Rubrique> findByNom(String name);
    
}
