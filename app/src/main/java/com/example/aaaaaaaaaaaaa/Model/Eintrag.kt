package com.example.aaaaaaaaaaaaa.Model

import com.example.aaaaaaaaaaaaa.Model.Eintrag
import java.sql.Date
import java.util.ArrayList

open class Eintrag {
    //von hier bis zu setKategorie alles methoden aus dem model
    var id: Int
        private set
    var name: String
    var betrag: Float
    var date: java.util.Date
    var waerhung: String
    var kategorie: String
    var deleted: Date?

    constructor(
        id: Int,
        name: String,
        betrag: Float,
        date: java.util.Date,
        kategorie: String,
        waehrung: String,
        deleted: Date?
    ) {
        this.id = id
        this.name = name
        this.betrag = betrag
        this.date = date
        this.waerhung = waehrung
        this.kategorie = kategorie
        this.deleted = deleted
    }

    constructor(
        id: Int,
        name: String,
        betrag: Float,
        date: java.util.Date,
        kategorie: String,
        waehrung: String
    ) {
        this.id = id
        this.name = name
        this.betrag = betrag
        this.date = date
        waerhung = waehrung
        this.kategorie = kategorie
        deleted = null
    }

    constructor(id: Int, name: String, betrag: Float, date: java.util.Date, waehrung: String) {
        this.id = id
        this.name = name
        this.betrag = betrag
        this.date = date
        waerhung = waehrung
        kategorie = ""
        deleted = null
    }

    constructor(id: Int, name: String, betrag: Float, date: java.util.Date) {
        this.id = id
        this.name = name
        this.betrag = betrag
        this.date = date
        waerhung = ""
        kategorie = ""
        deleted = null
    }

    companion object {
        var eintragArrayList = ArrayList<Eintrag>()

        fun getEintragForId(passedEintragId: Int): Eintrag? {
            for (eintrag in eintragArrayList) {
                if (eintrag.id == passedEintragId) return eintrag
            }
            return null
        }

        fun nonDeletedEintrag(): ArrayList<Eintrag> {
            val nonDeleted = ArrayList<Eintrag>()
            for (eintrag in eintragArrayList) {
                if (eintrag.deleted == null) nonDeleted.add(eintrag)
            }
            return nonDeleted
        }
    }
}