package com.aurel.carlib.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aurel.carlib.helper.TypeMenu;
import com.aurel.carlib.model.Menu;
import com.aurel.carlib.repository.MenuRepository;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    
    @Autowired
	private MenuRepository repo;
	
	@GetMapping("/")
    public ResponseEntity<List<Menu>> getAllTutorials(@RequestParam(required = false) TypeMenu type) {
    try {
        List<Menu> menus = new ArrayList<Menu>();

        if (type == null)
        repo.findAll().forEach(menus::add);
        else
        repo.findByType(type).forEach(menus::add);

        if (menus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(menus, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
