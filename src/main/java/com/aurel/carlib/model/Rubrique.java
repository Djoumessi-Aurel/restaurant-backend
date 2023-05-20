package com.aurel.carlib.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.aurel.carlib.helper.Functions;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rubrique", 
        uniqueConstraints=
        @UniqueConstraint(columnNames={"nom"}))
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Rubrique {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rubrique")
    private int id;

	@NonNull
    private String nom;
    @NonNull
    private String description;
    
    @JsonIgnore
    @OneToMany(mappedBy = "rubrique", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<MenuItem> items = new ArrayList<MenuItem>();


    public void addItem(MenuItem item) {
        if(Functions.contains(items, item)) return;

        items.add(item);
        item.setRubrique(this);
    }
    public void removeItem(MenuItem item) {
        items.remove(item);
        item.setRubrique(null);
    }

}
