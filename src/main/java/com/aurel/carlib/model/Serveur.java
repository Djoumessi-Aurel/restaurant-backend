package com.aurel.carlib.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.aurel.carlib.helper.Functions;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "serveur")
@DynamicUpdate
@RequiredArgsConstructor
@Getter @Setter
public class Serveur extends Personnel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_serveur")
    private int id;

    public Serveur(String nom, String identifiant, String motdepasse){
        super(nom, identifiant, motdepasse);
    }

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
