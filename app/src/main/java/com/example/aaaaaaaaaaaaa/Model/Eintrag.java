package com.example.aaaaaaaaaaaaa.Model;

import static java.sql.Types.NULL;

import java.sql.Date;
import java.util.ArrayList;

public class Eintrag {

    private int id;

    private String name;

    private float betrag;

    private String date;

    private String waehrung;

    private String kategorie;

    public Date deleted;

    public static ArrayList<Eintrag> eintragArrayList = new ArrayList<>();

    public Eintrag(int id, String name, float betrag, String date , String kategorie,String waehrung, Date deleted) {
        this.id = id;
        this.name = name;
        this.betrag = betrag;
        this.date = date;
        this.waehrung = waehrung;
        this.kategorie =  kategorie;
        this.deleted = deleted;

    }
    public Eintrag(int id, String name, float betrag, String date,String kategorie,String waehrung) {
        this.id = id;
        this.name = name;
        this.betrag = betrag;
        this.date = date;
        this.waehrung = waehrung;
        this.kategorie =  kategorie;
        this.deleted = null;

    }

    public Eintrag(int id, String name, float betrag, String date, String waehrung) {
        this.id = id;
        this.name = name;
        this.betrag = betrag;
        this.date = date;
        this.waehrung = waehrung;
        this.kategorie = null;
        this.deleted = null;


    }
    public Eintrag(int id, String name, float betrag, String date) {
        this.id = id;
        this.name = name;
        this.betrag = betrag;
        this.date = date;
        this.waehrung = null;
        this.kategorie = null;
        this.deleted = null;
    }



//von hier bis zu setKategorie alles methoden aus dem model
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBetrag() {
        return this.betrag;
    }

    public void setBetrag(float betrag) {
        this.betrag = betrag;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWaerhung() {
        return this.waehrung;
    }

    public void setWaerhung(String waehrung) {
        this.waehrung = waehrung;
    }

    public String getKategorie() {
        return this.kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
    /////////////////
    public static Eintrag getEintragForId(int passedEintragId)
    {
        for (Eintrag eintrag : eintragArrayList)
        {
            if(eintrag.getId() == passedEintragId)
                return eintrag;
        }

        return null;
    }


    public static ArrayList<Eintrag> nonDeletedEintrag() {
            ArrayList<Eintrag> nonDeleted = new ArrayList<Eintrag>();
            for (Eintrag eintrag : eintragArrayList) {
                if (eintrag.deleted == null) nonDeleted.add(eintrag);
            }
            return nonDeleted;
        }





}