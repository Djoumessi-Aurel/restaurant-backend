package com.aurel.carlib.code;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class ModePaiement {

    @NonNull
    private String nom;
    @NonNull
    private String description;
    
    private Set<Commande> commandes = new HashSet<Commande>();

    public static Set<ModePaiement> listeModesPaiement = new HashSet<ModePaiement>(); //Variable de l'application

    public void addCommande(Commande cmd) {
        commandes.add(cmd);
        cmd.setModePaiement(this);
    }
    public void removeCommande(Commande cmd) {
        commandes.remove(cmd);
        cmd.setModePaiement(null);
    }

    public static void ajouterMode(ModePaiement mode){
        listeModesPaiement.add(mode);
    }

    public static void supprimerMode(ModePaiement mode){
        listeModesPaiement.remove(mode);
    }
}
