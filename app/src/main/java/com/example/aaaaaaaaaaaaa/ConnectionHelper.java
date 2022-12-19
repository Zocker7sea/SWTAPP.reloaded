package com.example.aaaaaaaaaaaaa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionHelper extends SQLiteOpenHelper {

    public ConnectionHelper(Context context) {
        super(context, "Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Eintrag(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name TEXT," +
                " betrag FLOAT," +
                " datum DATE," +
                " kategorie TEXT," +
                " währung TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL(" drop Table if exists Eintrag");

    }
    public boolean insertEintrag(String name, Float betrag, String date, String kategorie, String waehrung) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("betrag", betrag);
        cv.put("datum", date);
        cv.put("kategorie", kategorie);
        cv.put("währung", waehrung);
        long result = DB.insert("Eintrag", null, cv);
        if(result == -1) {
            return  false;
        } else {
            return true;
        }
    }

    public void clear() {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL(" Delete from Eintrag");

    }

    public boolean updateEintrag(String name, Float betrag, String date, String kategorie, String waehrung) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("betrag", betrag);
        cv.put("datum", date);
        cv.put("kategorie", kategorie);
        cv.put("währung", waehrung);
        Cursor cursor = DB.rawQuery("Select * from Eintrag where name = ?",new String[] {});
        if (cursor.getCount() > 0) {
            long result = DB.update("Eintrag", cv, "name = ?", new  String[] {name});
            if(result == -1) {
                return  false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery(" Select * from Eintrag",null);
        return cursor;

    }
//    public Cursor getDatum() {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select datum from Eintrag",null);
//        return cursor;
//    }
}
