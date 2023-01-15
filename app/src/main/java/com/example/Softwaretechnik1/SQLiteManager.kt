package com.example.Softwaretechnik1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.Softwaretechnik1.Model.Eintrag
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SQLiteManager(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        createTable1(db)
        createTable2(db)
    }

    private fun createTable1(db: SQLiteDatabase) {
        val sql: StringBuilder = StringBuilder()
            .append("CREATE TABLE ")
            .append(TABLE_NAME)
            .append("(")
            .append(ID_FIELD)
            .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
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
        db.execSQL(sql.toString())
    }
    private fun createTable2(db: SQLiteDatabase) {
        val sql =
            "CREATE TABLE $AUSGABE ($ID_FIELD INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_FIELD TEXT,$BETRAG_FIELD FLOAT,$DATUM_FIELD DATUM,$KATEGORIE_FIELD TEXT,$WAEHRUNG_FIELD TEXT)"
        db.execSQL(sql)
    }

//    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
//        val sql: StringBuilder
//        sql = StringBuilder()
//            .append("CREATE TABLE ")
//            .append(TABLE_NAME)
//            .append("(")
//            .append(COUNTER)
//            .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
//            .append(ID_FIELD)
//            .append(" INT, ")
//            .append(NAME_FIELD)
//            .append(" TEXT, ")
//            .append(BETRAG_FIELD)
//            .append(" FLOAT, ")
//            .append(DATUM_FIELD)
//            .append(" DATE, ")
//            .append(KATEGORIE_FIELD)
//            .append(" TEXT, ")
//            .append(WAEHRUNG_FIELD)
//            .append(" TEXT)")
//        sqLiteDatabase.execSQL(sql.toString())
//    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

//        switch (oldVersion)
//        {
//            case 1:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT");
//            case 2:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT")
//        }
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + AUSGABE + "'");
        if (oldVersion < newVersion) {
            createTable1(sqLiteDatabase)
            createTable2(sqLiteDatabase)
        }
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
    fun addAusgabetoDatabase(eintrag: Eintrag): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        //contentValues.put(ID_FIELD, eintrag.getId())
        contentValues.put(NAME_FIELD, eintrag.getName())
        contentValues.put(BETRAG_FIELD, eintrag.getBetrag())
        contentValues.put(DATUM_FIELD, getStringFromDate(eintrag.getDate()))
        contentValues.put(KATEGORIE_FIELD, eintrag.getKategorie())
        contentValues.put(WAEHRUNG_FIELD, eintrag.getWaehrung())
        //contentValues.put(DELETED_FIELD, getStringFromDate(eintrag.getDeleted()))
        val result = sqLiteDatabase.insert(AUSGABE, null, contentValues)
        return result != -1L
    }


    fun clear() {
        val sqLiteDatabase = this.writableDatabase
        sqLiteDatabase.delete(TABLE_NAME,null,null)
        sqLiteDatabase.delete(AUSGABE,null,null)
    }

    fun getAusgaben() : Float{
        var res : Float = 0f
        val cal : Calendar = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal[Calendar.MONTH]
        cal.set(year,month,1)
        val start = cal.time
        cal.set(year,month ,31)
        val end = cal.time
        val dateFormat =SimpleDateFormat("yyyy-MM-dd")
        val startDate = dateFormat.format(start)
        val endDate = dateFormat.format(end)
        val sqLiteDatabase = this.readableDatabase
        val query3 = "SELECT SUM(betrag) FROM "+ AUSGABE+" WHERE "+ DATUM_FIELD + " >= '" + startDate + "' AND " + DATUM_FIELD + "<= '"+endDate+"'"
        val cursor = sqLiteDatabase.rawQuery(query3,null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getFloat(0)
            }
        } else {
            res = 0F
        }
        return res
    }

    fun getAusgabenVon(von : String) : Float{
        var res : Float = 0f
        val sqLiteDatabase = this.readableDatabase
        val query =
            "SELECT SUM(betrag) FROM " + AUSGABE + " WHERE " + DATUM_FIELD + " >= '"+von+"'"
        val cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getFloat(0)
            }
        } else {
            res = 0F
        }
        return res
    }
    fun getAusgabenBis(bis: String) : Float{
        var res : Float = 0f
        val sqLiteDatabase = this.readableDatabase
        val query =
            "SELECT SUM(betrag) FROM " + AUSGABE + " WHERE " + DATUM_FIELD + " <= '"+bis+"'"
        val cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getFloat(0)
            }
        } else {
            res = 0F
        }
        return res
    }
    fun getAusgabenVonBis(von: String,bis: String) : Float{
        var res : Float = 0f
        val sqLiteDatabase = this.readableDatabase
        val query =
            "SELECT SUM(betrag) FROM " + AUSGABE + " WHERE " + DATUM_FIELD + " >= '"+von+"' and " + DATUM_FIELD + " <= '"+bis+"'"
        val cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getFloat(0)
            }
        } else {
            res = 0F
        }
        return res
    }
    fun getEinnahmen() : Float{
        var res : Float = 0f
        val cal : Calendar = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal[Calendar.MONTH]
        cal.set(year,month,1)
        val start = cal.time
        cal.set(year,month ,31)
        val end = cal.time
        val dateFormat =SimpleDateFormat("yyyy-MM-dd")
        val startDate = dateFormat.format(start)
        val endDate = dateFormat.format(end)
        val sqLiteDatabase = this.readableDatabase
        val query = "SELECT SUM(betrag) FROM "+ TABLE_NAME+" WHERE "+ DATUM_FIELD + " >= '" + startDate + "' AND " + DATUM_FIELD + "<= '"+endDate+"'"
        val cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getFloat(0)
            }
        } else {
            res = 0F
        }
        return res
    }
    fun getEinnahmenVon(von : String) : Float{
        var res : Float = 0f
        val sqLiteDatabase = this.readableDatabase
        val query =
            "SELECT SUM(betrag) FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " >= '"+von+"'"
        val cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getFloat(0)
            }
        } else {
            res = 0F
        }
        return res
    }
    fun getEinnahmenBis(bis: String) : Float{
        var res : Float = 0f
        val sqLiteDatabase = this.readableDatabase
        val query =
            "SELECT SUM(betrag) FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " <= '"+bis+"'"
        val cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getFloat(0)
            }
        } else {
            res = 0F
        }
        return res
    }
    fun getEinnahmenVonBis(von: String,bis: String) : Float{
        var res : Float = 0f
        val sqLiteDatabase = this.readableDatabase
        val query =
            "SELECT SUM(betrag) FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " >= '"+von+"' and " + DATUM_FIELD + " <= '"+bis+"'"
        val cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor.count != 0) {
            if(cursor.moveToNext()) {
                res = cursor!!.getFloat(0)
            }
        } else {
            res = 0F
        }
        return res
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

    fun populateEintragListArrayVon(von : String) : ArrayList<Eintrag>  {
        val newlist = ArrayList<Eintrag>()
        val sqLiteDatabase = this.writableDatabase
        val query =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " >= '"+von+"' ORDER BY "+ DATUM_FIELD + " ASC"
        sqLiteDatabase.rawQuery(query, null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(0)
                    val name = result.getString(1)
                    val betrag = result.getFloat(2)
                    val stringdatum = result.getString(3)
                    val kategorie = result.getString(4)
                    val waehrung = result.getString(5)
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
    fun populateEintragListArrayBis(bis : String) : ArrayList<Eintrag>  {
        val newlist = ArrayList<Eintrag>()
        val sqLiteDatabase = this.writableDatabase
        val query =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " <= '"+bis+"' ORDER BY "+ DATUM_FIELD + " ASC"
        sqLiteDatabase.rawQuery(query, null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(0)
                    val name = result.getString(1)
                    val betrag = result.getFloat(2)
                    val stringdatum = result.getString(3)
                    val kategorie = result.getString(4)
                    val waehrung = result.getString(5)
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

    fun populateEintragListArrayVonBIs(von : String, bis : String) : ArrayList<Eintrag>  {
        val newlist = ArrayList<Eintrag>()
        val sqLiteDatabase = this.readableDatabase
        //select * from eintrag where datum >= '09.01.2023'  and datum <= '14.01.2023'
        val query =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + DATUM_FIELD + " >= '"+von+"' and " + DATUM_FIELD + " <= '"+bis+"'ORDER BY "+ DATUM_FIELD + " ASC"
        sqLiteDatabase.rawQuery(query, null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(0)
                    val name = result.getString(1)
                    val betrag = result.getFloat(2)
                    val stringdatum = result.getString(3)
                    val kategorie = result.getString(4)
                    val waehrung = result.getString(5)
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
        val dateFormat =SimpleDateFormat("yyyy-MM-dd")
        val startDate = dateFormat.format(start)
        val endDate = dateFormat.format(end)
        val query3 = "SELECT * FROM "+ TABLE_NAME+" WHERE "+ DATUM_FIELD + " >= '" + startDate + "' AND " + DATUM_FIELD + "<= '"+endDate+"' ORDER BY "+ DATUM_FIELD + " ASC"
        sqLiteDatabase.rawQuery(query3,null).use { result ->
            if (result.count != 0) {
                while (result.moveToNext()) {
                    val id = result.getInt(0)
                    val name = result.getString(1)
                    val betrag = result.getFloat(2)
                    val stringdatum = result.getString(3)
                    val kategorie = result.getString(4)
                    val waehrung = result.getString(5)
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
        private const val AUSGABE = "Ausgabe"
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


        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

        fun instanceOfDatabase(context: Context?): SQLiteManager? {
            if (sqLiteManager == null) sqLiteManager = SQLiteManager(context)
            return sqLiteManager
        }
    }
}