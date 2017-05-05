package com.example.lkhedma.firstaid;

/**
 * Created by LKHEDMA on 13/04/2017.
 */

public class Accident {

    private String id;
    private String nom;
    private String icon;


    public Accident(String id, String nom, String icon) {
        this.id = id;
        this.nom = nom;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
