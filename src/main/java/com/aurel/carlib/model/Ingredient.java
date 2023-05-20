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
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "ingredient")
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingredient")
    private int id;

    @NonNull
    private String nom;
    @NonNull
    private Integer cout;
    @NonNull
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "id_menuItem")
    private MenuItem menuItem;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderIngredient> orderIngredients = new ArrayList<OrderIngredient>();

    
    public void setMenuItem(MenuItem newMenuItem) {
        if (menuItem != newMenuItem) {
        MenuItem oldMenuItem = menuItem;
        menuItem = newMenuItem;
        if (newMenuItem != null)        newMenuItem.ajouterIngredient(this);
        if (oldMenuItem != null)        oldMenuItem.supprimerIngredient(this);
        }
    }

    public void addOrderIngredient(OrderIngredient oIngredient) {
        if(Functions.contains(orderIngredients, oIngredient)) return;

        orderIngredients.add(oIngredient);
        oIngredient.setIngredient(this);
    }

    public void removeOrderIngredient(OrderIngredient oIngredient) {
        orderIngredients.remove(oIngredient);
        oIngredient.setIngredient(null);
    }

}
