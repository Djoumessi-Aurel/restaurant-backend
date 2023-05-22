package com.aurel.resto.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.resto.helper.TypeMenu;
import com.aurel.resto.model.Menu;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

    public Iterable<Menu> findByType(TypeMenu type);
    
}
