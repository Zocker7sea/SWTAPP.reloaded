package com.example.aaaaaaaaaaaaa.Model;

public class Einstellung {
    private int id;

    private String name;

    private Boolean isDarkmode;
    private  Waehrung waehrung;

    public Einstellung(int id, String name, Boolean isDarkmode, Waehrung waehrung)  {
        this.id = id;
        this.name = name;
        this.isDarkmode = isDarkmode;
        this.waehrung = waehrung;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDarkemode() {
        return this.isDarkmode;
    }

    public void setDarkmode(Boolean bDarkmode) {
        isDarkmode = bDarkmode;
    }

    public Waehrung getWaerhung() {
		return waehrung;
    }

    public void setWaerhung(Waehrung waehrung) {
        this.waehrung = waehrung;
    }
}
