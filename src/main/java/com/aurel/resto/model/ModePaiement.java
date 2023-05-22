package com.aurel.resto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.aurel.resto.helper.Functions;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "modePaiement", 
            uniqueConstraints=
            @UniqueConstraint(columnNames={"nom"}))
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class ModePaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modePaiement")
    private int id;

    @NonNull
    private String nom;
    @NonNull
    private String description;
    
    @JsonIgnore
    @OneToMany(mappedBy = "modePaiement", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Commande> commandes = new ArrayList<Commande>();

    public static List<ModePaiement> listeModesPaiement = new ArrayList<ModePaiement>(); //Variable de l'application

    public void addCommande(Commande cmd) {
        if(Functions.contains(commandes, cmd)) return;

        commandes.add(cmd);
        cmd.setModePaiement(this);
    }
    public void removeCommande(Commande cmd) {
        commandes.remove(cmd);
        cmd.setModePaiement(null);
    }

    public static void ajouterMode(ModePaiement mode){
        if(Functions.contains(listeModesPaiement, mode)) return;

        listeModesPaiement.add(mode);
    }

    public static void supprimerMode(ModePaiement mode){
        listeModesPaiement.remove(mode);
    }
}
