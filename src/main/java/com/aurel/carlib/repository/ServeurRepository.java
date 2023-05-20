package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.carlib.model.Serveur;

public interface ServeurRepository extends CrudRepository<Serveur, Integer> {
    public Iterable<Serveur> findByNom(String name);
}
