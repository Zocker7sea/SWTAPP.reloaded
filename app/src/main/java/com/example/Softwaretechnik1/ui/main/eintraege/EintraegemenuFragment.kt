package com.example.Softwaretechnik1.ui.main.eintraege

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.example.Softwaretechnik1.R
import com.example.Softwaretechnik1.ui.main.ausgaben.AusgabenhinzufuegenFragment
import com.example.Softwaretechnik1.ui.main.einnahmen.EinnahmenhinzufuegenFragment
import com.example.Softwaretechnik1.ui.main.einnahmen.RegelmaessigeeinnahmenFragment

class EintraegemenuFragment : Fragment(R.layout.fragment_eintraegemenu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnmenu = view.findViewById<ImageButton>(R.id.eintraegemenu2)
        btnmenu.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, EintraegeAnzeigenFragment())?.commitNow()
        }
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