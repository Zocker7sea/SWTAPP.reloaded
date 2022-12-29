package com.example.aaaaaaaaaaaaa.ui.main.singleitems

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.ui.main.menues.SideeingeklapptFragment
import com.example.aaaaaaaaaaaaa.ui.main.menues.SideeintraegeFragment

class WaehrungsrechnerFragment : Fragment(R.layout.fragment_waehrungsrechner) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

     fun berechne(waehrung1 : String, waehrung2 : String, betrag : Float) : Float {
         return betrag;
     }

    private fun waehrungsrechner() : WaehrungsrechnerFragment {
        return  this;
    }
    public  fun getInstance() : WaehrungsrechnerFragment {
        return this;
    }



}