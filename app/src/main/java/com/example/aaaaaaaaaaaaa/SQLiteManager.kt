package com.example.aaaaaaaaaaaaa

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.aaaaaaaaaaaaa.Model.Eintrag
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SQLiteManager(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val sql: StringBuilder
        sql = StringBuilder()
            .append("CREATE TABLE ")
            .append(TABLE_NAME)
            .append("(")
            .append(COUNTER)
            .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
            .append(ID_FIELD)
            .append(" INT, ")
            .append(NAME_FIELD)
            .append(" TEXT, ")
            .append(BETRAG_FIELD)
            .append(" FLOAT, ")
            .append(DATUM_FIELD)
            .append(" DATE, ")
            .append(KATEGORIE_FIELD)
            .append(" TEXT, ")
            .append(WAEHRUNG_FIELD)
            .append(" TEXT, ")
            .append(DELETED_FIELD)
            .append(" DATE)")
        sqLiteDatabase.execSQL(sql.toString())
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

//        switch (oldVersion)
//        {
//            case 1:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT");
//            case 2:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT");
//        }
    }

    fun insertEintrag(
        eintrag: Eintrag
    ): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_FIELD, eintrag.id)
        contentValues.put(NAME_FIELD, eintrag.name)
        contentValues.put(BETRAG_FIELD, eintrag.betrag)
        contentValues.put(DATUM_FIELD, getStringFromDate(eintrag.date))
        contentValues.put(KATEGORIE_FIELD, eintrag.kategorie)
        contentValues.put(WAEHRUNG_FIELD, eintrag.waerhung)
        contentValues.put(DELETED_FIELD, getStringFromDate(eintrag.deleted))
        val result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
        return if (result == -1L) {
            false
        } else {
            true
        }
    }

    fun addEintragToDatabase(eintrag: Eintrag) : Boolean {

        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_FIELD, eintrag.id)
        contentValues.put(NAME_FIELD, eintrag.name)
        contentValues.put(BETRAG_FIELD, eintrag.betrag)
        contentValues.put(DATUM_FIELD,getStringFromDate(eintrag.date))
        contentValues.put(KATEGORIE_FIELD, eintrag.kategorie)
        contentValues.put(WAEHRUNG_FIELD, eintrag.waerhung)
        contentValues.put(DELETED_FIELD, getStringFromDate(eintrag.deleted))
        val result: Long = sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
        return if (result == -1L) {
            false
        } else {
            true
        }

       // sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
    }
    fun getIdFromCounter(): Int {
        var res : Int = 1
        val sqLiteDatabase = this.readableDatabase
        val query = "SELECT COUNT(*) FROM " + TABLE_NAME
        val cursor = sqLiteDatabase.rawQuery(query, null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getInt(0)
            }
        } else {
            res = 1
        }

        return res
    }
fun getKategorieForNameETC(namee : String, betrag : Float, datum : Date): String {
    var kat : String = ""
    val sqLiteDatabase = this.readableDatabase
    val projection = arrayOf<String>(KATEGORIE_FIELD)
    val selection: String = NAME_FIELD + "=? AND " + BETRAG_FIELD + "=? "//AND " + DATUM_FIELD + "=?"
    val selectionArgs = arrayOf<String>(namee,betrag.toString())//,datum.toString())
   // val query = "SELECT kategorie FROM " + TABLE_NAME + " where " + NAME_FIELD + "=" + namee //+ "  AND " + BETRAG_FIELD + " =? AND " + DATUM_FIELD + " =?"
    val cursor2 = sqLiteDatabase.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

    if(cursor2.count != 0) {
        if(cursor2.moveToNext()) {
            kat = cursor2!!.getString(0)
        }
    } else {
        kat = " "
    }
    return kat
}
    fun getWaehrunFromNameETC(namee : String, betrag : Float, datum : Date): String {
        var wae = ""
        val sqLiteDatabase = this.readableDatabase
        val projection = arrayOf<String>(WAEHRUNG_FIELD)
        val selection: String = NAME_FIELD + "=? AND " + BETRAG_FIELD + "=? "//AND " + DATUM_FIELD + "=?"
        val selectionArgs = arrayOf<String>(namee,betrag.toString())//,datum.toString())
        val cursor2 = sqLiteDatabase.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if(cursor2.count != 0) {
            if(cursor2.moveToNext()) {
                wae = cursor2!!.getString(0)
            }
        } else {
            wae = " "
        }
        return wae
    }

    fun getListViewItemsId(): ArrayList<Int> {
        val sqLiteDatabase = this.writableDatabase
        val query = "SELECT COUNT(COUNTER) FROM " + TABLE_NAME
        val cursor: Cursor = sqLiteDatabase.rawQuery(query, null)
        val idlist : ArrayList<Int> = ArrayList<Int>()
        while (cursor.moveToNext()){
            idlist.add(cursor.getInt(0))
        }
        return idlist
    }
    fun getListViewItemsName(): ArrayList<String> {
        val sqLiteDatabase = this.writableDatabase
        val query = "SELECT name FROM " + TABLE_NAME
        val cursor: Cursor = sqLiteDatabase.rawQuery(query, null)
        val namelist : ArrayList<String> = ArrayList<String>()
        while (cursor.moveToNext()){
            namelist.add(cursor.getString(0))
        }
        return namelist
    }
    fun getListViewItemsBetrag(): ArrayList<Float> {
        val sqLiteDatabase = this.writableDatabase
        val query = "SELECT name FROM " + TABLE_NAME
        val cursor: Cursor = sqLiteDatabase.rawQuery(query, null)
        val betraglist : ArrayList<Float> = ArrayList<Float>()
        while (cursor.moveToNext()){
            betraglist.add(cursor.getFloat(0))
        }
        return betraglist
    }
    fun getListViewItemsDatum(): ArrayList<Date> {
        val sqLiteDatabase = this.writableDatabase
        val query = "SELECT name FROM " + TABLE_NAME
        val cursor: Cursor = sqLiteDatabase.rawQuery(query, null)
        val datumlist : ArrayList<Date> = ArrayList<Date>()
        while (cursor.moveToNext()){
            val datee  : String = cursor.getString(0)
            getDateFromString(datee)?.let { datumlist.add(it) }
        }
        return datumlist
    }

    fun getdata(): Cursor {
        val DB = this.readableDatabase
        return DB.rawQuery(" Select * from Eintrag",null)
    }

    fun populateEintragListArray() {
        val sqLiteDatabase = this.readableDatabase
        sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(0)
                    val name = result.getString(2)
                    val betrag = result.getFloat(3)
                    val stringdatum = result.getString(4)
                    val datum = getDateFromString(stringdatum)
                   // val sqldate =  java.sql.Date(datum!!.time)
                    //val kategorie = result.getString(5)
                    //val waehrung = result.getString(6)
                    val eintrag = datum?.let {
                        Eintrag(id, name, betrag,
                            it
                        )
                    }
                    if (eintrag != null) {
                        Eintrag.eintragArrayList.add(eintrag)
                    }
                }
            }
        }
    }

    fun updateNoteInDB(eintrag: Eintrag) {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_FIELD, eintrag.id)
        contentValues.put(NAME_FIELD, eintrag.name)
        contentValues.put(BETRAG_FIELD, eintrag.betrag)
        contentValues.put(DATUM_FIELD,getStringFromDate(eintrag.date))
        contentValues.put(KATEGORIE_FIELD, eintrag.kategorie)
        contentValues.put(WAEHRUNG_FIELD, eintrag.waerhung)
        contentValues.put(DELETED_FIELD, getStringFromDate(eintrag.deleted))
        sqLiteDatabase.update(
            TABLE_NAME,
            contentValues,
            ID_FIELD + " =? ",
            arrayOf(eintrag.id.toString())
        )
    }

    private fun getStringFromDate(date: Date?): String? {
        return if (date == null) null else dateFormat.format(date)
    }

    private fun getDateFromString(string: String): Date? {
        return try {
            dateFormat.parse(string)
        } catch (e: ParseException) {
            null
        } catch (e: NullPointerException) {
            null
        }
    }

    companion object {
        private var sqLiteManager: SQLiteManager? = null
        private const val DATABASE_NAME = "EintragDb"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Eintrag"
        private const val COUNTER = "Counter"
        private const val ID_FIELD = "id"
        private const val NAME_FIELD = "name"
        private const val BETRAG_FIELD = "betrag"
        private const val DATUM_FIELD = "datum"
        private const val KATEGORIE_FIELD = "kategorie"
        private const val WAEHRUNG_FIELD = "waehrung"
        private const val DELETED_FIELD = "deleted"


        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy")

        fun instanceOfDatabase(context: Context?): SQLiteManager? {
            if (sqLiteManager == null) sqLiteManager = SQLiteManager(context)
            return sqLiteManager
        }
    }
}