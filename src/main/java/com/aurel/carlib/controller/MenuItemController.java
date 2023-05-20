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

import com.aurel.carlib.model.Menu;
import com.aurel.carlib.model.MenuItem;
import com.aurel.carlib.model.Rubrique;
import com.aurel.carlib.repository.MenuItemRepository;
import com.aurel.carlib.repository.MenuRepository;
import com.aurel.carlib.repository.RubriqueRepository;

@RestController
@RequestMapping("/api/menuItem")
public class MenuItemController {
    
    @Autowired
	private MenuItemRepository repo;
    @Autowired
	private MenuRepository menuRepo;
    @Autowired
	private RubriqueRepository rubriqueRepo;
	
	@GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems(@RequestParam(required = false) String nom) {
    try {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        if (nom == null)
        repo.findAll().forEach(menuItems::add);
        else
        repo.findByNomContains(nom).forEach(menuItems::add);

        if (menuItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byMenu/{menuId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByMenu(@PathVariable("menuId") int menuId) {
    try {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        repo.findByMenuId(menuId).forEach(menuItems::add);

        if (menuItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byRubrique/{rubriqueId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRubrique(@PathVariable("rubriqueId") int rubriqueId) {
    try {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        repo.findByRubriqueId(rubriqueId).forEach(menuItems::add);

        if (menuItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable("id") int id) {
        Optional<MenuItem> menuItem = repo.findById(id);

        if (menuItem.isPresent()) {
        return new ResponseEntity<>(menuItem.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createMenuItem(@RequestBody MenuItem menuItem,
        @RequestParam int menuId, @RequestParam int rubriqueId) {
        try {
            Optional<Menu> menu = menuRepo.findById(menuId);
            if(!menu.isPresent()){
                return new ResponseEntity<String>("Le menu " + menuId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }
            Optional<Rubrique> rubrique = rubriqueRepo.findById(rubriqueId);
            if(!rubrique.isPresent()){
                return new ResponseEntity<>("La rubrique " + rubriqueId + " n'existe pas!", HttpStatus.NOT_FOUND);
            }

            MenuItem item  = new MenuItem(menuItem.getNom(), menuItem.getCout(), menuItem.getDescription());
            item.setMenu(menu.get());
            item.setRubrique(rubrique.get());

        return new ResponseEntity<>(repo.save(item), HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
