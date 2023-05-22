package com.aurel.resto.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.resto.model.Serveur;

public interface ServeurRepository extends CrudRepository<Serveur, Integer> {
    public Iterable<Serveur> findByNom(String name);
}
