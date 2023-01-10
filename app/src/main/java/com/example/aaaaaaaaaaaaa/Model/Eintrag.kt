package com.example.aaaaaaaaaaaaa.Model

import java.sql.Date

open class Eintrag {
    //von hier bis zu setKategorie alles methoden aus dem model
    private var id: Int
        private set
    private var name: String
    private var betrag: Float
    private var date: java.util.Date
    private var waerhung: String
    private var kategorie: String
    private var deleted: Date?

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
    fun getId(): Int {
        return this.id
    }

    fun setId(id: Int) {
        this.id = id
    }
    fun getName(): String {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getBetrag(): Float {
        return this.betrag
    }

    fun setBetrag(betrag: Float) {
        this.betrag = betrag
    }

    fun setDate(date: Date) {
        this.date = date
    }
    fun getDate(): java.util.Date {
        return date
    }
    fun setKategorie(kategorie: String) {
        this.kategorie = kategorie
    }
    fun getKategorie(): String {
        return this.kategorie
    }
    fun setWaehrung(waehrung: String) {
        this.waerhung = waehrung
    }
    fun getWaehrung(): String {
        return this.waerhung
    }
    fun setDeleted(date: Date?) {
        this.deleted = date
    }
    fun getDeleted(): Date? {
        return this.deleted
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