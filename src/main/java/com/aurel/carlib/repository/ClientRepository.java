package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    public Iterable<Client> findByNom(String name);
    
}
