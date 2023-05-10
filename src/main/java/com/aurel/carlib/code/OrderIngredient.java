package com.aurel.carlib.code;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class OrderIngredient {
    
    @NonNull
    private Integer quantite;
    
    private OrderItem orderItem;
    private Ingredient ingredient;

    public void setOrderItem(OrderItem newOrderItem) {
        if (orderItem != newOrderItem) {
        OrderItem oldOrderItem = orderItem;
        orderItem = newOrderItem;
        if (newOrderItem != null)        newOrderItem.ajouterIngredient(this);
        if (oldOrderItem != null)        oldOrderItem.supprimerIngredient(this);
        }
    }

    public void setIngredient(Ingredient newIngredient) {
        if (ingredient != newIngredient) {
        Ingredient oldIngredient = ingredient;
        ingredient = newIngredient;
        if (newIngredient != null)        newIngredient.addOrderIngredient(this);
        if (oldIngredient != null)        oldIngredient.removeOrderIngredient(this);
        }
    }

}
