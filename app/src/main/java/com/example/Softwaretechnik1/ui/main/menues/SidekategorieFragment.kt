package com.example.Softwaretechnik1.ui.main.menues

import StatistikFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.example.Softwaretechnik1.R
import com.example.Softwaretechnik1.ui.main.ausgaben.AusgabenhinzufuegenFragment
import com.example.Softwaretechnik1.ui.main.einnahmen.EinnahmenhinzufuegenFragment
import com.example.Softwaretechnik1.ui.main.kategorie.KategorieanzeigenFragment
import com.example.Softwaretechnik1.ui.main.kategorie.KategorieerstellenFragment
import com.example.Softwaretechnik1.ui.main.kategorie.KategorieloeschenFragment
import com.example.Softwaretechnik1.ui.main.singleitems.EinstellungenFragment
import com.example.Softwaretechnik1.ui.main.singleitems.WaehrungsrechnerFragment


class SidekategorieFragment : Fragment(R.layout.side_menu_kategorie) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnuebersicht = view.findViewById<Button>(R.id.übersichts)
        btnuebersicht.setOnClickListener {
            // Toast.makeText(activity,"test",Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SideuebersichtFragment())?.commitNow()
        }

        val btnkategorie = view.findViewById<Button>(R.id.kategorie)
        btnkategorie.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SideeingeklapptFragment())?.commitNow()
        }

        val btnkategorieerstellen = view.findViewById<Button>(R.id.kategorie1)
        btnkategorieerstellen.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, KategorieerstellenFragment())?.commitNow()
        }

        val btnkategorieanzeigen = view.findViewById<Button>(R.id.kategorie2)
        btnkategorieanzeigen.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, KategorieanzeigenFragment())?.commitNow()
        }

        val btnkategorieloeschen = view.findViewById<Button>(R.id.kategorie3)
        btnkategorieloeschen.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, KategorieloeschenFragment())?.commitNow()
        }

        val btneintraege = view.findViewById<Button>(R.id.eintraege)
        btneintraege.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SideeintraegeFragment())?.commitNow()
        }


        val btnsparziel = view.findViewById<Button>(R.id.sparziel)
        btnsparziel.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SidesparzielFragment())?.commitNow()
        }

        val btnwaehrungsrechner = view.findViewById<Button>(R.id.währungsrechner)
        btnwaehrungsrechner.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, WaehrungsrechnerFragment())?.commitNow()
        }

        val btnstatistik = view.findViewById<Button>(R.id.statistik)
        btnstatistik.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, StatistikFragment())?.commitNow()
        }

        val btneinstellungen = view.findViewById<Button>(R.id.einstellungen)
        btneinstellungen.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, EinstellungenFragment())?.commitNow()
        }

        val btnplus = view.findViewById<Button>(R.id.plusbtn)
        btnplus.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, EinnahmenhinzufuegenFragment())?.commitNow()
        }

        val btnminus = view.findViewById<Button>(R.id.minusbtn)
        btnminus.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, AusgabenhinzufuegenFragment())?.commitNow()
        }

    }
}