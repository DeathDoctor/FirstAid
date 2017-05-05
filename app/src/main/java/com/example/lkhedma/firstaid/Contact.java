package com.example.lkhedma.firstaid;

/**
 * Created by LKHEDMA on 07/04/2017.
 */

public class Contact {

    private String nom;
    private int numero;
    private String specification;

    public Contact(String nom, int numero, String specification) {
        this.nom = nom;
        this.numero = numero;
        this.specification = specification;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
