package com.example.aaaaaaaaaaaaa.ui.main.einnahmen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.aaaaaaaaaaaaa.Model.Eintrag
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.SQLiteManager
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EinnahmenhinzufuegenFragment : Fragment(R.layout.fragment_einnahmenhinzufuegen) {
    //private var name: EditText = null
//    private var betrag: EditText = null
//    private var datum: EditText? = null
//    private var kategorie: EditText? = null
//    private var waehrung: EditText? = null

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //edit felder und button
        val name: EditText = view.findViewById(R.id.texteditname)
        val betrag: EditText = view.findViewById(R.id.texteditbetrag)
        val datum: EditText = view.findViewById(R.id.texteditdatum)
        val kategorie: EditText = view.findViewById(R.id.texteditkategorie)
        val waehrung: EditText = view.findViewById(R.id.texteditwaehrung)
        val insertbtn = view.findViewById<Button>(R.id.buttonsave)

        val li: LayoutInflater = layoutInflater
        val layout: View = li.inflate(R.layout.toast_erfolgreich, view.findViewById(R.id.toast))
        val toast: Toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.TOP, 10, 0)
        toast.view = layout

        insertbtn.setOnClickListener {
            val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
            val nametxt = name.text.toString()
            val betragtxt = getFloat(betrag.text.toString())
            var datumtxt = datum.text.toString()
            val kategorietxt = kategorie.text.toString()
            var waehrungtxt = waehrung.text.toString()
            val id : Int= Eintrag.eintragArrayList.size
            //var changes: Boolean = true
            //while (changes) {
                //set default währung
                if (waehrungtxt.isEmpty()) {
                    waehrungtxt = "€"
                } else if (nametxt.length < 2) {
                    //checks if name is to short
                    name.setError("Name ist zu kurz -> min zeichen 2 !!!")
                } else if (nametxt.isEmpty()) {
                    //checks if name is empty
                    name.error = "Feld muss gefüllt !!!"
                } else if (betrag.text.toString().isEmpty()) {
                    //checks if betrag is empty
                    betrag.error = "Feld muss gefüllt !!!"
                } else if (datumtxt.isEmpty()) {
                    //set datum to current datum if empty
                    val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy")
                    val currentDate = Date()
                    datumtxt = dateFormat.format(currentDate)
                } else {
                    //wenn alles oben abgearbeitet wurde while loop verlassen
                    //changes = false
                    //neuen eintrag erstellen
                    val newEintrag =
                        Eintrag(id, nametxt, betragtxt, datumtxt, kategorietxt, waehrungtxt)
                    //eintrag in die database inserten
                    val checkinsertdata = sqLiteManager!!.addEintragToDatabase(newEintrag)
                    if (checkinsertdata) {
                        //wenn es geklappt hat, custom toast aufrufen und den eintrag in die eintrasliste hinzufuegen
                        toast.show()
                        Eintrag.eintragArrayList.add(newEintrag)
                        //break
                        //nach erfolgreichen eintragen auf haupt menu?
                        //Toast.makeText(activity,"New Entry Inserted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "New Entry not Inserted", Toast.LENGTH_SHORT).show()
                       // break
                    }
                }
           // }


        }


//        insertbtn.setOnClickListener {
//
//            val sqLiteManager = SQLiteManager(requireActivity())
//
//            val nameTXT = name.text.toString()
//            val betragTXT = getFloat(betrag.text.toString())
//            var datumTXT = datum.text.toString()
//            val kategorieTXT = kategorie.text.toString()
//            var waehrungTXT = waehrung.text.toString()
//            val id = Eintrag.eintragArrayList.size
//            val newEintrag = Eintrag(id,nameTXT,betragTXT,datumTXT,kategorieTXT,waehrungTXT)
//            val checkinsertdata : Boolean = sqLiteManager.addEintragToDatabase(newEintrag)
//
//            if(waehrungTXT.isEmpty()) {
//                waehrungTXT = "€"
//            } else if(nameTXT.length < 2){
//                name.setError("Name ist zu kurz -> min zeichen 2 !!!")
//            } else if(nameTXT.isEmpty()){
//                name.error = "Feld muss gefüllt !!!"
//            } else if(betrag.text.toString().isEmpty()){
//                betrag.error = "Feld muss gefüllt !!!"
//            } else if(datumTXT.isEmpty()){
//                datumTXT = "12.12.2022"
//                //datum.error = "Feld muss gefüllt !!!"
//            } else if(kategorieTXT.length > 20){
//                kategorie.error = "Name ist lang !!!"
//            } else {
//                if(checkinsertdata) {
//                    Eintrag.eintragArrayList.add(newEintrag)
//                    toast.show()
//
//                    //Toast.makeText(activity,"New Entry Inserted", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(activity,"New Entry not Inserted", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

////new try
//        insertbtn.setOnClickListener {
//            val sql = SQLiteManager(requireActivity())
//        }


        //old
//        insertbtn.setOnClickListener {
//            val DB : ConnectionHelper = ConnectionHelper(requireActivity())
//
//
//            val nameTXT : String = name.text.toString()
//            val betragTXT : Float = getFloat(betrag.text.toString())
//            val datumTXT : String = datum.text.toString()
//            val kategorieTXT : String = kategorie.text.toString()
//            var waehrungTXT : String = waehrung.text.toString()
//
//            //Toast.makeText(activity,kategorieTXT,Toast.LENGTH_SHORT).show()
//
//            val checkinsertdata : Boolean = DB.insertEintrag(nameTXT,betragTXT,
//                datumTXT,kategorieTXT,waehrungTXT)
//
//
//            if(waehrungTXT.isEmpty()) {
//                waehrungTXT = "€"
//            } else if(nameTXT.length < 2){
//                name.setError("Name ist zu kurz -> min zeichen 2 !!!")
//            } else if(nameTXT.isEmpty()){
//                name.error = "Feld muss gefüllt !!!"
//            } else if(datumTXT.isEmpty()){
//                datum.error = "Feld muss gefüllt !!!"
//            } else if(kategorieTXT.length > 20){
//                kategorie.error = "Name ist lang !!!"
//            } else {
//                if(checkinsertdata) {
//
//                    toast.show()
//
//                   // Toast.makeText(activity,"New Entry Inserted", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(activity,"New Entry not Inserted", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    }

    fun getFloat(intString: String): Float {
        var res: Float = 0f
        try {
            res = java.lang.Float.valueOf(intString.toString())
        } catch (e: java.lang.NumberFormatException) {
            e.printStackTrace()
        }
        return res
    }

    fun getDateFromString(dateString: String?): Date? {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        return try {
            sdf.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    private fun getStringFromDate(date: Date?): String? {
        return if (date == null) null else SQLiteManager.dateFormat.format(date)
    }


}

