package com.example.aaaaaaaaaaaaa.Model

import java.sql.Date

open class Eintrag {
    //von hier bis zu setKategorie alles methoden aus dem model
    private var id: Int
        private set
    private var name: String
    private var betrag: Float
    private var date: java.util.Date
    private lateinit var waerhung: String
    private lateinit var kategorie: String



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
        this.waerhung = waehrung
        this.kategorie = kategorie
    }

    constructor(id: Int, name: String, betrag: Float, date: java.util.Date, waehrung: String) {
        this.id = id
        this.name = name
        this.betrag = betrag
        this.date = date
        waerhung = waehrung
    }

    constructor(id: Int, name: String, betrag: Float, date: java.util.Date) {
        this.id = id
        this.name = name
        this.betrag = betrag
        this.date = date
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



}