package com.example.lkhedma.firstaid;

/**
 * Created by LKHEDMA on 02/05/2017.
 */

public class GesteSecour {

    String description;
    String titre_diagnostic;

    public GesteSecour(String description, String titre_diagnostic) {
        this.description = description;
        this.titre_diagnostic = titre_diagnostic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre_diagnostic() {
        return titre_diagnostic;
    }

    public void setTitre_diagnostic(String titre_diagnostic) {
        this.titre_diagnostic = titre_diagnostic;
    }
}
