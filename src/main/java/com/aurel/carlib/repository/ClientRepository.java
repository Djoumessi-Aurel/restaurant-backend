package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.carlib.model.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    public Iterable<Client> findByNom(String name);
    
}
