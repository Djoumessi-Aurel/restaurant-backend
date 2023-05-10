package com.aurel.carlib.code;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Client {
    
    @NonNull
    private String nom;

    private Set<Commande> commandes = new HashSet<Commande>();
    
    public void passerCommande(Commande c){
        this.addCommande(c);
        c.enregistrerCommande();
    }

    public void addCommande(Commande cmd){
        commandes.add(cmd);
        cmd.setClient(this);
    }

    public void removeCommande(Commande cmd){
        commandes.remove(cmd);
        cmd.setClient(null);
    }

}
