package com.aurel.carlib.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurel.carlib.model.Product;
import com.aurel.carlib.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }

    public Iterable<Product> getProductsByName(String name){
        return productRepository.findByName(name);
    }

    public Iterable<Product> getProductsByNameLike(String name){
        return productRepository.findByNameLike(name);
    }

    public Iterable<Product> getProductsByCategoriesName(String name){
        return productRepository.findByCategoriesName(name);
    }

    public Iterable<Product> getProductsByCategoriesNameLike(String name){
        return productRepository.findByCategoriesNameLike(name);
    }

    public Iterable<Product> getProductsByMinCost(Integer cost){
        return productRepository.findByCostNative(cost);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(int id){
        productRepository.deleteById(id);
    }

}
