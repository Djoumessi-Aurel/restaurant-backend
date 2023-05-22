package com.aurel.resto.model;

import lombok.Getter;
import lombok.Setter;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderItem")
@DynamicUpdate
@Getter @Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orderItem")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "id_menuItem")
    private MenuItem menuItem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;

    @OneToMany(mappedBy = "orderItem", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderIngredient> orderIngredients = new ArrayList<OrderIngredient>();

    public void setMenuItem(MenuItem newMenuItem) {
        if (menuItem != newMenuItem) {
        MenuItem oldMenuItem = menuItem;
        menuItem = newMenuItem;
        if (newMenuItem != null)        newMenuItem.addOrderItem(this);
        if (oldMenuItem != null)        oldMenuItem.removeOrderItem(this);
        }
    }

    public void setCommande(Commande newCommande) {
        if (commande != newCommande) {
        Commande oldCommande = commande;
        commande = newCommande;
        if (newCommande != null)        newCommande.ajouterItem(this);
        if (oldCommande != null)        oldCommande.supprimerItem(this);
        }
    }

    public int getCoutAvecIngredients(){
        int coutIngredients = 0;

        for(OrderIngredient oIng: orderIngredients){
            coutIngredients += oIng.getQuantite() * oIng.getIngredient().getCout();
        }

        return coutIngredients + menuItem.getCout();
    }

    public void ajouterIngredient(OrderIngredient ing){
        if(Functions.contains(orderIngredients, ing)) return;

        orderIngredients.add(ing); // le probleme est ici, on arrive pas a ajouter l'ingredient
        ing.setOrderItem(this);
    }
    public void supprimerIngredient(OrderIngredient ing){
        orderIngredients.remove(ing);
        ing.setOrderItem(null);
    }

}
