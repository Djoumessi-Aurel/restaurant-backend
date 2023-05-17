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
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "client")
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private int id;
    
    @NonNull
    private String nom;

    @OneToMany(mappedBy = "client", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Commande> commandes = new ArrayList<Commande>();


    public void addCommande(Commande cmd){
        if(Functions.contains(commandes, cmd)) return;
        commandes.add(cmd);
        cmd.setClient(this);
    }

    public void removeCommande(Commande cmd){
        commandes.remove(cmd);
        cmd.setClient(null);
    }

}
