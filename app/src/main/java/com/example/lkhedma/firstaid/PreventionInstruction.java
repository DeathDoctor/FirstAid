package com.example.lkhedma.firstaid;

/**
 * Created by LKHEDMA on 18/05/2017.
 */

public class PreventionInstruction {

    String titre;
    String sousTitre;
    String icon1;
    String icon2;
    String description;

    public PreventionInstruction(String titre, String sousTitre, String icon1, String icon2, String description) {
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.icon1 = icon1;
        this.icon2 = icon2;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getIcon1() {
        return icon1;
    }

    public void setIcon1(String icon1) {
        this.icon1 = icon1;
    }

    public String getIcon2() {
        return icon2;
    }

    public void setIcon2(String icon2) {
        this.icon2 = icon2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
