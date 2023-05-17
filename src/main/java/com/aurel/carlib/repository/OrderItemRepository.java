package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    public Iterable<OrderItem> findByMenuItemId(int id);
    public Iterable<OrderItem> findByCommandeId(int id);
    
}
