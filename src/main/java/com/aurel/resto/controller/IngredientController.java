package com.aurel.resto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurel.resto.model.Ingredient;
import com.aurel.resto.model.MenuItem;
import com.aurel.resto.repository.IngredientRepository;
import com.aurel.resto.repository.MenuItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {
    
    @Autowired
	private IngredientRepository repo;
    @Autowired
	private MenuItemRepository menuItemRepo;
	
	@GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients(@RequestParam(required = false) String nom) {
    try {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        if (nom == null)
        repo.findAll().forEach(ingredients::add);
        else
        repo.findByNomContains(nom).forEach(ingredients::add);

        if (ingredients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/byMenuItem/{menuItemId}")
    public ResponseEntity<List<Ingredient>> getIngredientsByMenuItem(@PathVariable("menuItemId") int menuItemId) {
    try {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        repo.findByMenuItemId(menuItemId).forEach(ingredients::add);

        if (ingredients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") int id) {
        Optional<Ingredient> ingredient = repo.findById(id);

        if (ingredient.isPresent()) {
        return new ResponseEntity<>(ingredient.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createIngredient(@RequestBody Ingredient ingredient,
        @RequestParam int menuItemId) {
        try {
            Optional<MenuItem> menuItem = menuItemRepo.findById(menuItemId);
            if(!menuItem.isPresent()){
                return new ResponseEntity<>("Le menuItem " + menuItemId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            Ingredient ing  = new Ingredient(ingredient.getNom(), ingredient.getCout(), ingredient.getDescription());
            ing.setMenuItem(menuItem.get());

        return new ResponseEntity<>(repo.save(ing), HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
