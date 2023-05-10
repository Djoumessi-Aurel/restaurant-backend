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
public class Ingredient {

    @NonNull
    private String nom;
    @NonNull
    private Integer cout;
    @NonNull
    private String description;
    
    private Set<OrderIngredient> orderIngredients = new HashSet<OrderIngredient>();

    public static Set<Ingredient> listeIngredients = new HashSet<Ingredient>(); //Variable de l'application

    public void addOrderIngredient(OrderIngredient oIngredient) {
        orderIngredients.add(oIngredient);
        oIngredient.setIngredient(this);
    }
    public void removeOrderIngredient(OrderIngredient oIngredient) {
        orderIngredients.remove(oIngredient);
        oIngredient.setIngredient(null);
    }

    
    public static void ajouterIngredient(Ingredient ing){
        listeIngredients.add(ing);
    }

    public static void supprimerIngredient(Ingredient ing){
        listeIngredients.remove(ing);
    }

}
