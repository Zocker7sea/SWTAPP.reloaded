package com.example.aaaaaaaaaaaaa.ui.main.einnahmen

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.aaaaaaaaaaaaa.ConnectionHelper
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.ui.main.menues.SideuebersichtFragment

class EinnahmenhinzufuegenFragment : Fragment(R.layout.fragment_einnahmenhinzufuegen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name : EditText = view.findViewById(R.id.texteditname)
        val betrag : EditText = view.findViewById(R.id.texteditbetrag)
        val datum : EditText = view.findViewById(R.id.texteditdatum)
        val kategorie : EditText = view.findViewById(R.id.texteditkategorie)
        val waehrung : EditText = view.findViewById(R.id.texteditwaehrung)
        val DB : ConnectionHelper = ConnectionHelper(requireActivity())
        val insertbtn = view.findViewById<Button>(R.id.buttonsave)
        insertbtn.setOnClickListener {
            val nameTXT : String = name.text.toString()
            val betragTXT : Float = getFloat(betrag.text.toString())
            val datumTXT : String = datum.text.toString()
            val kategorieTXT : String = kategorie.text.toString()
            var waehrungTXT : String = waehrung.text.toString()

            //Toast.makeText(activity,kategorieTXT,Toast.LENGTH_SHORT).show()

            val checkinsertdata : Boolean = DB.insertEintrag(nameTXT,betragTXT,
                datumTXT,kategorieTXT,waehrungTXT)


            if(waehrungTXT.isEmpty()) {
                waehrungTXT = "€"
            } else if(nameTXT.length < 2){
                name.setError("Name ist zu kurz -> min zeichen 2 !!!")
            } else if(nameTXT.isEmpty()){
                name.error = "Feld muss gefüllt !!!"
            } else if(datumTXT.isEmpty()){
                datum.error = "Feld muss gefüllt !!!"
            } else if(kategorieTXT.length > 20){
                kategorie.error = "Name ist lang !!!"
            } else {
                if(checkinsertdata) {
                    Toast.makeText(activity,"New Entry Inserted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity,"New Entry not Inserted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun getFloat(intString: String): Float {
        var res : Float = 0f
        try {
            res = java.lang.Float.valueOf(intString.toString())
        } catch (e: java.lang.NumberFormatException) {
            e.printStackTrace()
        }
        return res
    }
    }

