package com.aurel.resto.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.resto.model.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    public Iterable<Client> findByNom(String name);
    
}
