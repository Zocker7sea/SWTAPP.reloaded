package com.example.aaaaaaaaaaaaa.ui.main.eintraege

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.ui.main.ausgaben.AusgabenhinzufuegenFragment
import com.example.aaaaaaaaaaaaa.ui.main.einnahmen.EinnahmenhinzufuegenFragment
import com.example.aaaaaaaaaaaaa.ui.main.einnahmen.RegelmaessigeeinnahmenFragment
import com.example.aaaaaaaaaaaaa.ui.main.menues.SideuebersichtFragment

class EintraegemenuFragment : Fragment(R.layout.fragment_eintraegemenu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnREEIN = view.findViewById<Button>(R.id.btnREE)
        btnREEIN.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, RegelmaessigeeinnahmenFragment())?.commitNow()
        }
        val btnREAUS = view.findViewById<Button>(R.id.btnRAE)
        btnREAUS.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, RegelmaessigeeinnahmenFragment())?.commitNow()
        }
        val btnEE = view.findViewById<Button>(R.id.btnEE)
        btnEE.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, EinnahmenhinzufuegenFragment())?.commitNow()
        }
        val btnAE = view.findViewById<Button>(R.id.btnAE)
        btnAE.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, AusgabenhinzufuegenFragment())?.commitNow()
        }
    }
}

//class EintraegesuchenFragment : Fragment(R.layout.fragment_eintraegesuchen) {
//
//
//
//}