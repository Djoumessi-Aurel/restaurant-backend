package com.aurel.resto.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.resto.model.Rubrique;

public interface RubriqueRepository extends CrudRepository<Rubrique, Integer> {

    public Iterable<Rubrique> findByNom(String name);
    
}
