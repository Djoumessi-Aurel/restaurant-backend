package com.aurel.carlib.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurel.carlib.model.Category;
import com.aurel.carlib.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Iterable<Category> getCategoriesByNameLike(String name){
        return categoryRepository.findByNameLike(name);
    }

    public Iterable<Category> getCategoriesByProductsNameLike(String name){
        return categoryRepository.findByProductsNameLike(name);
    }

    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(int id){
        categoryRepository.deleteById(id);
    }

}
