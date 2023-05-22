package com.aurel.resto.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.aurel.resto.helper.Functions;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "serveur")
@DiscriminatorValue("S")
@DynamicUpdate
@RequiredArgsConstructor
@Getter @Setter
public class Serveur extends Personnel{


    public Serveur(String nom, String identifiant, String motdepasse){
        super(nom, identifiant, motdepasse);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "serveur", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Commande> commandes = new ArrayList<Commande>();
    
    public void validerCommande(Commande c){
        this.addCommande(c);
        c.validerCommande();
    }

    public void addCommande(Commande cmd){
        if(Functions.contains(commandes, cmd)) return;

        commandes.add(cmd);
        cmd.setServeur(this);
    }

    public void removeCommande(Commande cmd){
        commandes.remove(cmd);
        cmd.setServeur(null);
    }

}
