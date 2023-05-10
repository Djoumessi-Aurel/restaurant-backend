package com.aurel.carlib.code;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Serveur extends Personnel{

    private Set<Commande> commandes = new HashSet<Commande>();
    
    public void validerCommande(Commande c){
        this.addCommande(c);
        c.validerCommande();
    }

    public void addCommande(Commande cmd){
        commandes.add(cmd);
        cmd.setServeur(this);
    }

    public void removeCommande(Commande cmd){
        commandes.remove(cmd);
        cmd.setServeur(null);
    }

}
