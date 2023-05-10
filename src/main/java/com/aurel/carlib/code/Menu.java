package com.aurel.carlib.code;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import com.aurel.carlib.helper.TypeMenu;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Menu {

    @NonNull
    private TypeMenu type;

    private Set<MenuItem> items = new HashSet<MenuItem>();

    public static Set<Menu> listeMenus = new HashSet<Menu>(); //Variable de l'application

    public void ajouterItem(MenuItem item){
        items.add(item);
        item.setMenu(this);
    }

    public void supprimerItem(MenuItem item){
        items.remove(item);
        item.setMenu(null);
    }

    public Set<MenuItem> getListeItems(){
        return items;
    }

    public static void ajouterMenu(Menu menu){
        listeMenus.add(menu);
    }

    public static void supprimerMenu(Menu menu){
        listeMenus.remove(menu);
    }
    
}
