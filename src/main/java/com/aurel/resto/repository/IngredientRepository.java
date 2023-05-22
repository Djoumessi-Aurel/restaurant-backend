package com.aurel.resto.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.resto.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    public Iterable<Ingredient> findByNom(String name);
    public Iterable<Ingredient> findByNomContains(String name);
    public Iterable<Ingredient> findByMenuItemId(int id);
    
}
