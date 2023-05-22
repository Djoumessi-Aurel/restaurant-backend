package com.aurel.resto.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.resto.model.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {

    public Iterable<MenuItem> findByNom(String name);
    public Iterable<MenuItem> findByNomContains(String name);
    public Iterable<MenuItem> findByRubriqueId(int id);
    public Iterable<MenuItem> findByMenuId(int id);
    
}
