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

import com.aurel.carlib.model.OrderIngredient;
import com.aurel.carlib.model.Ingredient;
import com.aurel.carlib.model.OrderItem;
import com.aurel.carlib.repository.OrderItemRepository;
import com.aurel.carlib.repository.IngredientRepository;
import com.aurel.carlib.repository.OrderIngredientRepository;

@RestController
@RequestMapping("/api/orderIngredient")
public class OrderIngredientController {
    
    @Autowired
	private OrderIngredientRepository repo;
    @Autowired
	private OrderItemRepository orderItemRepo;
    @Autowired
	private IngredientRepository ingredientRepo;
	
	@GetMapping
    public ResponseEntity<List<OrderIngredient>> getAllOrderIngredients() {
    try {
        List<OrderIngredient> orderIngredients = new ArrayList<OrderIngredient>();

        repo.findAll().forEach(orderIngredients::add);

        if (orderIngredients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderIngredients, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/byOrderItem/{orderItemId}")
    public ResponseEntity<List<OrderIngredient>> getOrderIngredientsByOrderItem(@PathVariable("orderItemId") int orderItemId) {
    try {
        List<OrderIngredient> orderIngredients = new ArrayList<OrderIngredient>();

        repo.findByOrderItemId(orderItemId).forEach(orderIngredients::add);

        if (orderIngredients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderIngredients, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byIngredient/{ingredientId}")
    public ResponseEntity<List<OrderIngredient>> getOrderIngredientsByIngredient(@PathVariable("ingredientId") int ingredientId) {
    try {
        List<OrderIngredient> orderIngredients = new ArrayList<OrderIngredient>();

        repo.findByIngredientId(ingredientId).forEach(orderIngredients::add);

        if (orderIngredients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderIngredients, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderIngredientById(@PathVariable("id") int id) {
        Optional<OrderIngredient> orderIngredient = repo.findById(id);

        if (orderIngredient.isPresent()) {
        return new ResponseEntity<>(orderIngredient.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>("OrderIngredient non trouvé!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrderIngredient(@RequestBody OrderIngredient orderIngredient, @RequestParam int orderItemId,
            @RequestParam int ingredientId) {
        try {
            Optional<OrderItem> orderItem = orderItemRepo.findById(orderItemId);
            if(!orderItem.isPresent()){
                return new ResponseEntity<>("Le orderItem " + orderItemId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            Optional<Ingredient> ingredient = ingredientRepo.findById(ingredientId);
            if(!ingredient.isPresent()){
                return new ResponseEntity<>("L'ingredient " + ingredientId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            OrderIngredient oIng  = new OrderIngredient(orderIngredient.getQuantite());
            oIng.setOrderItem(orderItem.get());
            oIng.setIngredient(ingredient.get());

        return new ResponseEntity<>(repo.save(oIng), HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
