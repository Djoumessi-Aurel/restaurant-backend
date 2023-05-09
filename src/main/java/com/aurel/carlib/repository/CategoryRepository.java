package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    public Iterable<Category> findByNameLike(String name);
    
    public Iterable<Category> findByProductsNameLike(String name);
    
}
