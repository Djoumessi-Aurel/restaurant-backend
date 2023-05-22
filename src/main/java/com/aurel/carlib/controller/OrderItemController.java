package com.aurel.carlib.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aurel.carlib.model.OrderItem;
import com.aurel.carlib.model.MenuItem;
import com.aurel.carlib.model.Commande;
import com.aurel.carlib.repository.CommandeRepository;
import com.aurel.carlib.repository.MenuItemRepository;
import com.aurel.carlib.repository.OrderItemRepository;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
    
    @Autowired
	private OrderItemRepository repo;
    @Autowired
	private CommandeRepository commandeRepo;
    @Autowired
	private MenuItemRepository menuItemRepo;
	
	@GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
    try {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        repo.findAll().forEach(orderItems::add);

        if (orderItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/byCommande/{commandeId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByCommande(@PathVariable("commandeId") int commandeId) {
    try {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        repo.findByCommandeId(commandeId).forEach(orderItems::add);

        if (orderItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byMenuItem/{menuItemId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByMenuItem(@PathVariable("menuItemId") int menuItemId) {
    try {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        repo.findByMenuItemId(menuItemId).forEach(orderItems::add);

        if (orderItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable("id") int id) {
        Optional<OrderItem> orderItem = repo.findById(id);

        if (orderItem.isPresent()) {
        return new ResponseEntity<>(orderItem.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>("OrderItem non trouvé!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrderItem(@RequestBody OrderItem orderItem, @RequestParam int commandeId,
            @RequestParam int menuItemId) {
        try {
            Optional<Commande> commande = commandeRepo.findById(commandeId);
            if(!commande.isPresent()){
                return new ResponseEntity<>("La commande " + commandeId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            Optional<MenuItem> menuItem = menuItemRepo.findById(menuItemId);
            if(!menuItem.isPresent()){
                return new ResponseEntity<>("Le menuItem " + menuItemId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            OrderItem oItem  = new OrderItem();
            oItem.setCommande(commande.get());
            oItem.setMenuItem(menuItem.get());

        return new ResponseEntity<>(repo.save(oItem), HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
