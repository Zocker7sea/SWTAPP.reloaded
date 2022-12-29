package com.example.aaaaaaaaaaaaa.Model;

import android.media.Image;

public class Kategorie {

    private int id;

    private String name;

    private Image bild;

    public Kategorie(int id, String name, Image bild) {
        this.id = id;
        this.name = name;
        this.bild = bild;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return this.bild;
    }

    public void setImage(Image bild) {
            this.bild = bild;
    }

}
