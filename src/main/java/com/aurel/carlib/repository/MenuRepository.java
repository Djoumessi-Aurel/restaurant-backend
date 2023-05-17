package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.helper.TypeMenu;
import com.aurel.carlib.model.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {

    public Iterable<Menu> findByType(TypeMenu type);
    
}
