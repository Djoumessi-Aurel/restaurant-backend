package com.aurel.resto.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.resto.model.OrderIngredient;

public interface OrderIngredientRepository extends CrudRepository<OrderIngredient, Integer> {

    public Iterable<OrderIngredient> findByIngredientId(int id);
    public Iterable<OrderIngredient> findByOrderItemId(int id);
    
}
