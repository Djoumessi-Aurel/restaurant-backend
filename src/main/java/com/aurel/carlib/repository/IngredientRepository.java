package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    public Iterable<Ingredient> findByNom(String name);
    public Iterable<Ingredient> findByMenuItemId(int id);
    
}
