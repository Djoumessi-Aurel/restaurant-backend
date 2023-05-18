package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.carlib.helper.StatutCommande;
import com.aurel.carlib.model.Commande;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {

    public Iterable<Commande> findByClientId(int id);
    public Iterable<Commande> findByClientNom(String client_name);
    public Iterable<Commande> findByStatut(StatutCommande statut);
    
}
