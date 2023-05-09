package com.aurel.carlib.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "produit")
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
    private int productId;

    @NonNull
    @Column(name = "nom")
    private String name;

    @NonNull
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "cout")
    private Integer cost;

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL, 
        orphanRemoval = true
        //fetch = FetchType.EAGER
        )
	List<Comment> comments = new ArrayList<Comment>();

    @ManyToMany(
        mappedBy = "products",
        cascade = { 
			CascadeType.PERSIST, 
			CascadeType.MERGE 
			}
        )
	private List<Category> categories = new ArrayList<>();


    public void addComment(Comment comment) {
		comments.add(comment);
		comment.setProduct(this);
	}
 
	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setProduct(null);
	}


}
