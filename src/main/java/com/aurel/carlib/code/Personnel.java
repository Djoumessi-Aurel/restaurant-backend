package com.aurel.carlib.code;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Personnel {

    @NonNull
    protected String nom;
    @NonNull
    protected String identifiant;
    @NonNull
    protected String motdepasse;

    public void authentifier(){

    }

}

