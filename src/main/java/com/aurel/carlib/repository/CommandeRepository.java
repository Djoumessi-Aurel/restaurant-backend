package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.helper.StatutCommande;
import com.aurel.carlib.model.Commande;

@Repository
public interface CommandeRepository extends CrudRepository<Commande, Integer> {

    public Iterable<Commande> findByClientId(int id);
    public Iterable<Commande> findByClientNom(String client_name);
    public Iterable<Commande> findByStatut(StatutCommande statut);
    
}
