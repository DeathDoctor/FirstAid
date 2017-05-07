package com.example.lkhedma.firstaid;

/**
 * Created by LKHEDMA on 06/05/2017.
 */

public class GesteSecours {

    private String icon;
    private String description;

    public GesteSecours(String icon, String description) {
        this.icon = icon;
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
