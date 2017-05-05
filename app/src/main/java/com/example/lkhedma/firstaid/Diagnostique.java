package com.example.lkhedma.firstaid;

/**
 * Created by LKHEDMA on 02/05/2017.
 */

public class Diagnostique {

    String nom;
    String gravite;

    public Diagnostique(String nom, String gravite) {
        this.nom = nom;
        this.gravite = gravite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }
}
