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
public class MenuItem {
    
    @NonNull
    private String nom;
    @NonNull
    private Integer cout;
    @NonNull
    private String description;

    private Rubrique rubrique;
    private Menu menu;
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();


    public void setRubrique(Rubrique newRubrique) {
        if (rubrique != newRubrique) {
        Rubrique oldRubrique = rubrique;
        rubrique = newRubrique;
        if (newRubrique != null)        newRubrique.addItem(this);
        if (oldRubrique != null)        oldRubrique.removeItem(this);
        }
    }

    public void setMenu(Menu newMenu) {
        if (menu != newMenu) {
        Menu oldMenu = menu;
        menu = newMenu;
        if (newMenu != null)        newMenu.ajouterItem(this);
        if (oldMenu != null)        oldMenu.supprimerItem(this);
        }
    }

    public void addOrderItem(OrderItem oItem) {
        orderItems.add(oItem);
        oItem.setMenuItem(this);
    }
    public void removeOrderItem(OrderItem oItem) {
        orderItems.remove(oItem);
        oItem.setMenuItem(null);
    }
        

}
