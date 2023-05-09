package com.aurel.carlib.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "commentaire")
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentaire_id")
    private int commentId;

    @NonNull
    @Column(name = "contenu")
    private String content;

    @ManyToOne(
        cascade = { 
            CascadeType.PERSIST, 
            CascadeType.MERGE 
            }
        )
    @JoinColumn(name = "produit_id")
    private Product product;
    
}
