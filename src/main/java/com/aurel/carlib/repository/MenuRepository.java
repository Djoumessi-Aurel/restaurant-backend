package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;

import com.aurel.carlib.helper.TypeMenu;
import com.aurel.carlib.model.Menu;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

    public Iterable<Menu> findByType(TypeMenu type);
    
}
