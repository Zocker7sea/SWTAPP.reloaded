package com.example.aaaaaaaaaaaaa

import android.annotation.SuppressLint
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
            .append(" TEXT)")
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

    fun addEintragToDatabase(eintrag: Eintrag) : Boolean {

        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_FIELD, eintrag.getId())
        contentValues.put(NAME_FIELD, eintrag.getName())
        contentValues.put(BETRAG_FIELD, eintrag.getBetrag())
        contentValues.put(DATUM_FIELD,getStringFromDate(eintrag.getDate()))
        contentValues.put(KATEGORIE_FIELD, eintrag.getKategorie())
        contentValues.put(WAEHRUNG_FIELD, eintrag.getWaehrung())
        val result: Long = sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
        return if (result == -1L) {
            false
        } else {
            true
        }

       // sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
    }
    fun clear() {
        val sqLiteDatabase = this.writableDatabase
        //val query  = "DELETE FROM " + TABLE_NAME
        sqLiteDatabase.delete(TABLE_NAME,null,null)
}


    @SuppressLint("Recycle")
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



    fun getdata(): Cursor {
        val DB = this.readableDatabase
        return DB.rawQuery(" Select * from Eintrag",null)
    }
    fun populateEintragListArrayVon(von : String) : ArrayList<Eintrag>  {
        val newlist = ArrayList<Eintrag>()
        val sqLiteDatabase = this.writableDatabase
        val query =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " >= '"+von+"' ORDER BY "+ DATUM_FIELD + " ASC"
        sqLiteDatabase.rawQuery(query, null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(1)
                    val name = result.getString(2)
                    val betrag = result.getFloat(3)
                    val stringdatum = result.getString(4)
                    val kategorie = result.getString(5)
                    val waehrung = result.getString(6)
                    val eintrag = getDateFromString(stringdatum)?.let {
                        Eintrag(
                            id,
                            name,
                            betrag,
                            it,
                            // kategorie,
                            // waehrung
                        )
                    }
                    if (eintrag != null) {
                        newlist.add(eintrag)
                    } else {
                        println("eintrag is null")
                    }
                }
            }
        }
        return newlist
    }
    fun populateEintragListArrayBis(bis : String) : ArrayList<Eintrag>  {
        val newlist = ArrayList<Eintrag>()
        val sqLiteDatabase = this.writableDatabase
        //val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        //val startDate = dateFormat.format(von)
        //val query2= "SELECT * FROM "+ TABLE_NAME + " WHERE " + DATUM_FIELD + " > ?"
        //val params = arrayOf<String>(bis)
        //sqLiteDatabase.rawQuery(query2, null).use { result ->
        val query =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " <= '"+bis+"' ORDER BY "+ DATUM_FIELD + " ASC"
        sqLiteDatabase.rawQuery(query, null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(1)
                    val name = result.getString(2)
                    val betrag = result.getFloat(3)
                    val stringdatum = result.getString(4)
                    val kategorie = result.getString(5)
                    val waehrung = result.getString(6)
                    val eintrag = getDateFromString(stringdatum)?.let {
                        Eintrag(
                            id,
                            name,
                            betrag,
                            it,
                            // kategorie,
                            // waehrung
                        )
                    }
                    if (eintrag != null) {
                        newlist.add(eintrag)
                    } else {
                        println("eintrag is null")
                    }
                }
            }
        }
        return newlist
    }

    fun populateEintragListArrayVonBIs(von : String, bis : String) : ArrayList<Eintrag>  {
        val newlist = ArrayList<Eintrag>()
        val sqLiteDatabase = this.writableDatabase
        //select * from eintrag where datum >= '09.01.2023'  and datum <= '14.01.2023'
        val query =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " >= '"+von+"' and " + DATUM_FIELD + " <= '"+bis+"'ORDER BY "+ DATUM_FIELD + " ASC"
        sqLiteDatabase.rawQuery(query, null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(1)
                    val name = result.getString(2)
                    val betrag = result.getFloat(3)
                    val stringdatum = result.getString(4)
                    val kategorie = result.getString(5)
                    val waehrung = result.getString(6)
                    val eintrag = getDateFromString(stringdatum)?.let {
                        Eintrag(
                            id,
                            name,
                            betrag,
                            it,
                            // kategorie,
                            // waehrung
                        )
                    }
                    if (eintrag != null) {
                        newlist.add(eintrag)
                    } else {
                        println("eintrag is null")
                    }
                }
            }
        }
        return newlist
    }

    fun populateEintragListArray() : ArrayList<Eintrag> {
        val newlist: ArrayList<Eintrag> = ArrayList<Eintrag>()
        val sqLiteDatabase = this.readableDatabase
        val cal : Calendar = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal[Calendar.MONTH]
        cal.set(year,month,1)
        val start = cal.time
        cal.set(year,month ,31)
        val end = cal.time
        val dateFormat = SimpleDateFormat("dd.MM.yyy")
        val startDate = dateFormat.format(start)
        val endDate = dateFormat.format(end)
        val query3 = "SELECT * FROM "+ TABLE_NAME+" WHERE "+ DATUM_FIELD + " >= '" + startDate + "' AND " + DATUM_FIELD + "<= '"+endDate+"' ORDER BY "+ DATUM_FIELD + " ASC"
        sqLiteDatabase.rawQuery(query3,null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(1)
                    val name = result.getString(2)
                    val betrag = result.getFloat(3)
                    val stringdatum = result.getString(4)
                    val kategorie = result.getString(5)
                    val waehrung = result.getString(6)
                    val eintrag = getDateFromString(stringdatum)?.let {
                        Eintrag(
                            id,
                            name,
                            betrag,
                            it,
                            kategorie,
                            waehrung
                        )
                    }
                    if (eintrag != null) {
                        newlist.add(eintrag)
                    } else {
                        println("eintrag is null")
                    }
                }
            }
        }
        return newlist
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


        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy")

        fun instanceOfDatabase(context: Context?): SQLiteManager? {
            if (sqLiteManager == null) sqLiteManager = SQLiteManager(context)
            return sqLiteManager
        }
    }
}