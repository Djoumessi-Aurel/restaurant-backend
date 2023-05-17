package com.aurel.carlib.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.aurel.carlib.helper.Functions;
import com.aurel.carlib.helper.TypeMenu;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private int id;

    @NonNull
    private TypeMenu type;

    @OneToMany(mappedBy = "menu", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<MenuItem> items = new ArrayList<MenuItem>();

    public void ajouterItem(MenuItem item){
        if(Functions.contains(items, item)) return;

        items.add(item);
        item.setMenu(this);
    }

    public void supprimerItem(MenuItem item){
        items.remove(item);
        item.setMenu(null);
    }

    public List<MenuItem> getListeItems(){
        return items;
    }
    
}
