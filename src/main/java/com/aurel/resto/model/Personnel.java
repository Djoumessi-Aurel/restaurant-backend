package com.aurel.resto.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "personnel")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type_personnel")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personnel")
    private int id;
    
    @NonNull
    protected String nom;
    @NonNull
    protected String identifiant;
    @NonNull
    protected String motdepasse;

    public boolean authentifier(String username, String password){
        // cette methode retoune true lorsque les identifiants sont correctes
        if(username == this.identifiant && password == this.motdepasse) {
            return true;
        }
        return false;
    }

}

