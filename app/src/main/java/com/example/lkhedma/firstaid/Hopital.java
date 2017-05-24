package com.example.lkhedma.firstaid;

/**
 * Created by LKHEDMA on 20/05/2017.
 */
public class Hopital {

    String nom;
    String photo;
    String wilaya;
    String quartier;
    String numero;
    String site;
    String latt;
    String longt;

    public Hopital(String nom, String photo, String quartier, String numero) {
        this.nom = nom;
        this.photo = photo;
        this.quartier = quartier;
        this.numero = numero;
    }

    public Hopital(String nom, String photo, String wilaya, String quartier, String numero, String site, String latt, String longt) {
        this.nom = nom;
        this.photo = photo;
        this.wilaya = wilaya;
        this.quartier = quartier;
        this.numero = numero;
        this.site = site;
        this.latt = latt;
        this.longt = longt;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLatt() {
        return latt;
    }

    public void setLatt(String latt) {
        this.latt = latt;
    }

    public String getLongt() {
        return longt;
    }

    public void setLongt(String longt) {
        this.longt = longt;
    }
}
