package com.example.Softwaretechnik1.ui.main.sparziel

import androidx.fragment.app.Fragment
import com.example.Softwaretechnik1.Model.Sparziel
import com.example.Softwaretechnik1.R


class SparzielanzeigenFragment : Fragment(R.layout.fragment_sparzielanzeigen) {

    public  fun getInstance() : SparzielanzeigenFragment {
        return this;
    }
    fun getSparziele(): List<Sparziel?>? {
        return null
    }
    fun getSparziel(id: Int): Sparziel? {
        return null
    }

    fun loescheSparziel(id: Int): Boolean? {
        return null
    }



}