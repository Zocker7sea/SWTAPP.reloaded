package com.example.aaaaaaaaaaaaa.Model;

import java.sql.Date;

public class regelmaessigeEintraege extends Eintrag {
    private  Abbuchungsintevall abbuchungsintevall;
    public regelmaessigeEintraege(int id, String name, float betrag, String date, String waehrung,Abbuchungsintevall abbuchungsintervall) {
        super(id, name, betrag, date, waehrung);
        this.abbuchungsintevall = abbuchungsintervall;
    }

}