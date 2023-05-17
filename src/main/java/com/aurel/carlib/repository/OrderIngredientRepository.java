package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.OrderIngredient;

@Repository
public interface OrderIngredientRepository extends CrudRepository<OrderIngredient, Integer> {

    public Iterable<OrderIngredient> findByIngredientId(int id);
    public Iterable<OrderIngredient> findByOrderItemId(int id);
    
}
