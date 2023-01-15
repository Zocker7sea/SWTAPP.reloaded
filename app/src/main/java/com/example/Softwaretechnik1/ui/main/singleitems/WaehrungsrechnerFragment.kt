package com.example.Softwaretechnik1.ui.main.singleitems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.Softwaretechnik1.R

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