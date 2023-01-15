package com.example.Softwaretechnik1.Model;

import java.sql.Date;

public class regelmaessigeEintraege extends Eintrag {
    private  Abbuchungsintevall abbuchungsintevall;
    public regelmaessigeEintraege(int id, String name, float betrag, Date date, String waehrung,Abbuchungsintevall abbuchungsintervall) {
        super(id, name, betrag, date, waehrung);
        this.abbuchungsintevall = abbuchungsintervall;
    }

}