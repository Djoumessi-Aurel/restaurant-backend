package com.aurel.carlib.code;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderItem {
    
    private MenuItem menuItem;
    private Commande commande;
    private Set<OrderIngredient> orderIngredients = new HashSet<OrderIngredient>();

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
        orderIngredients.add(ing);
        ing.setOrderItem(this);
    }
    public void supprimerIngredient(OrderIngredient ing){
        orderIngredients.remove(ing);
        ing.setOrderItem(null);
    }

}
