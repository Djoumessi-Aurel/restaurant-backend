package com.aurel.carlib.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Personnel {

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

