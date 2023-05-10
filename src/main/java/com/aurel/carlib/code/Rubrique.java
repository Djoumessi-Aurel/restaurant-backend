package com.aurel.carlib.code;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Rubrique {
    
    @NonNull
    private String nom;
    @NonNull
    private String description;
    
    private Set<MenuItem> items = new HashSet<MenuItem>();


    public void addItem(MenuItem item) {
        items.add(item);
        item.setRubrique(this);
    }
    public void removeItem(MenuItem item) {
        items.remove(item);
        item.setRubrique(null);
    }


}
