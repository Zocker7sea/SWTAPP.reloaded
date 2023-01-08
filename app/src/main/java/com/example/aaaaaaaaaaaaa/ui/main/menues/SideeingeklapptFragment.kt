package com.example.aaaaaaaaaaaaa.ui.main.menues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.ui.main.ausgaben.AusgabenhinzufuegenFragment
import com.example.aaaaaaaaaaaaa.ui.main.einnahmen.EinnahmenhinzufuegenFragment
import com.example.aaaaaaaaaaaaa.ui.main.singleitems.EinstellungenFragment
import com.example.aaaaaaaaaaaaa.ui.main.singleitems.StatistikFragment
import com.example.aaaaaaaaaaaaa.ui.main.singleitems.WaehrungsrechnerFragment

class SideeingeklapptFragment : Fragment(R.layout.side_menu) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnuebersicht = view.findViewById<Button>(R.id.übersichts)
        btnuebersicht.setOnClickListener {
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
                ?.replace(R.id.container, SidesparzielFragment())?.commitNow()
        }

        val btnwaehrungsrechner = view.findViewById<Button>(R.id.währungsrechner)
        btnwaehrungsrechner.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, WaehrungsrechnerFragment())?.commitNow()
        }

        val btnstatistik = view.findViewById<Button>(R.id.statistik)
//        requireView().findViewById<View>(R.id.statistik).setOnClickListener {
//            startActivity(
//                Intent(
//                    context,
//                    PieChartActivity::class.java
//                )
//            )
//        }
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