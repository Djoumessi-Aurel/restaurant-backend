package com.aurel.resto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurel.resto.helper.StatutCommande;
import com.aurel.resto.model.Client;
import com.aurel.resto.model.Commande;
import com.aurel.resto.model.ModePaiement;
import com.aurel.resto.model.Serveur;
import com.aurel.resto.repository.ClientRepository;
import com.aurel.resto.repository.CommandeRepository;
import com.aurel.resto.repository.ModePaiementRepository;
import com.aurel.resto.repository.ServeurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {
    
    @Autowired
	private CommandeRepository repo;
    @Autowired
	private ClientRepository clientRepo;
    @Autowired
	private ServeurRepository serveurRepo;
    @Autowired
	private ModePaiementRepository mpRepo;
	
	@GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes(@RequestParam(required = false) StatutCommande statut) {
    try {
        List<Commande> commandes = new ArrayList<Commande>();

        if (statut == null)
        repo.findAll().forEach(commandes::add);
        else
        repo.findByStatut(statut).forEach(commandes::add);

        if (commandes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/byClient/{clientId}")
    public ResponseEntity<List<Commande>> getCommandesByClient(@PathVariable("clientId") int clientId) {
    try {
        List<Commande> commandes = new ArrayList<Commande>();

        repo.findByClientId(clientId).forEach(commandes::add);

        if (commandes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommandeById(@PathVariable("id") int id) {
        Optional<Commande> commande = repo.findById(id);

        if (commande.isPresent()) {
        return new ResponseEntity<>(commande.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>("Commande non trouvée!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCommande(@RequestBody Commande commande, @RequestParam int clientId,
            @RequestParam int serveurId, @RequestParam int mpId) {
        try {
            Optional<Client> client = clientRepo.findById(clientId);
            if(!client.isPresent()){
                return new ResponseEntity<>("Le client " + clientId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            Optional<Serveur> serveur = serveurRepo.findById(serveurId);
            if(!serveur.isPresent()){
                return new ResponseEntity<>("Le serveur " + serveurId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            Optional<ModePaiement> mp = mpRepo.findById(mpId);
            if(!mp.isPresent()){
                return new ResponseEntity<>("Le mode de paiement " + mpId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            Commande cmd  = new Commande(commande.getNumeroTable());
            cmd.setStatut(StatutCommande.ENREGISTREE);
            cmd.setClient(client.get());
            cmd.setServeur(serveur.get());
            cmd.setModePaiement(mp.get());

        return new ResponseEntity<>(repo.save(cmd), HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/valider/{id}")
    public ResponseEntity<?> validerCommande(@PathVariable("id") int id) {
        try {
            Optional<Commande> commande = repo.findById(id);

            if (commande.isPresent()) {
            commande.get().setStatut(StatutCommande.VALIDEE);
            return new ResponseEntity<>(repo.save(commande.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("La commande " + id + " n'existe pas!", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
