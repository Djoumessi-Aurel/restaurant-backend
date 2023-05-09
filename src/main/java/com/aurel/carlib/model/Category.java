package com.aurel.carlib.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "categorie")
@DynamicUpdate
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorie_id")
    private int categoryId;

	@NonNull
    @Column(name = "nom")
    private String name;

    @ManyToMany(
    fetch = FetchType.LAZY,
	cascade = { 
			CascadeType.PERSIST, 
			CascadeType.MERGE 
			}	
	)
	@JoinTable(
			name = "categorie_produit",
			joinColumns = @JoinColumn(name = "categorie_id"), 	
			inverseJoinColumns = @JoinColumn(name = "produit_id")
	)
	private List<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
		products.add(product);
		product.getCategories().add(this);
	}
 
	public void removeProduit(Product product) {
		products.remove(product);
		product.getCategories().remove(this);
	}

    
}
