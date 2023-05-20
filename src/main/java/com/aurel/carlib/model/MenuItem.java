package com.aurel.carlib.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.aurel.carlib.helper.Functions;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menuItem")
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menuItem")
    private int id;
    
    @NonNull
    private String nom;
    @NonNull
    private Integer cout;
    @NonNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_rubrique")
    private Rubrique rubrique;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @JsonIgnore
    @OneToMany(mappedBy = "menuItem", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    @OneToMany(mappedBy = "menuItem", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();


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
        if(Functions.contains(orderItems, oItem)) return;

        orderItems.add(oItem);
        oItem.setMenuItem(this);
    }

    public void removeOrderItem(OrderItem oItem) {
        orderItems.remove(oItem);
        oItem.setMenuItem(null);
    }

    public void ajouterIngredient(Ingredient ing) {
        if(Functions.contains(ingredients, ing)) return;

        ingredients.add(ing);
        ing.setMenuItem(this);
    }

    public void supprimerIngredient(Ingredient ing) {
        ingredients.remove(ing);
        ing.setMenuItem(null);
    }
        
}
