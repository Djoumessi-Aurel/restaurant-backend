package com.aurel.carlib.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "orderIngredient")
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class OrderIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orderIngredient")
    private int id;
    
    @NonNull
    private Integer quantite;
    
    @ManyToOne
    @JoinColumn(name = "id_orderItem")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "id_ingredient")
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
