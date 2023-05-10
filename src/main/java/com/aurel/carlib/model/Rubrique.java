package com.aurel.carlib.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "rubrique", 
        uniqueConstraints=
        @UniqueConstraint(columnNames={"nom"}))
@DynamicUpdate
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Rubrique {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@NonNull
    private String nom;

    private String description;

}
