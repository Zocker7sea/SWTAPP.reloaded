package com.example.Softwaretechnik1.ui.main.menues

import StatistikFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.example.Softwaretechnik1.R
import com.example.Softwaretechnik1.ui.main.ausgaben.AusgabenhinzufuegenFragment
import com.example.Softwaretechnik1.ui.main.einnahmen.EinnahmenhinzufuegenFragment
import com.example.Softwaretechnik1.ui.main.singleitems.EinstellungenFragment
import com.example.Softwaretechnik1.ui.main.singleitems.WaehrungsrechnerFragment
import com.example.Softwaretechnik1.ui.main.sparziel.SparzielanzeigenFragment
import com.example.Softwaretechnik1.ui.main.sparziel.SparzielerstellenFragment


class SidesparzielFragment : Fragment(R.layout.side_menu_sparziel) {
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
                ?.replace(R.id.container, SidekategorieFragment())?.commitNow()
        }

        val btneintraege = view.findViewById<Button>(R.id.eintraege)
        btneintraege.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SideeintraegeFragment())?.commitNow()
        }

        val btnsparziel = view.findViewById<Button>(R.id.sparziel)
        btnsparziel.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SideeingeklapptFragment())?.commitNow()
        }
        val btnsparzielerstellen = view.findViewById<Button>(R.id.sparziel1)
        btnsparzielerstellen.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SparzielerstellenFragment())?.commitNow()
        }
        val btnsparzielanzeigen = view.findViewById<Button>(R.id.sparziel2)
        btnsparzielanzeigen.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SparzielanzeigenFragment())?.commitNow()
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