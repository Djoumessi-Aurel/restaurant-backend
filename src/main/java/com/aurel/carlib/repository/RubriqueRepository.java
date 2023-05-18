package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.carlib.model.Rubrique;

public interface RubriqueRepository extends CrudRepository<Rubrique, Integer> {

    public Iterable<Rubrique> findByNom(String name);
    
}
