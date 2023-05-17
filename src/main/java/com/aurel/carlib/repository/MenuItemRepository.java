package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.MenuItem;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {

    public Iterable<MenuItem> findByNom(String name);
    public Iterable<MenuItem> findByRubriqueId(int id);
    public Iterable<MenuItem> findByMenuId(int id);
    
}
