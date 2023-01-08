package com.example.aaaaaaaaaaaaa.ui.main.einnahmen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.aaaaaaaaaaaaa.Model.Eintrag
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.SQLiteManager
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class EinnahmenhinzufuegenFragment : Fragment(R.layout.fragment_einnahmenhinzufuegen) {


    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //edit felder und button
        val name: EditText = view.findViewById(R.id.texteditname)
        val betrag: EditText = view.findViewById(R.id.texteditbetrag)
        //val datum : EditText = view.findViewById(R.id.texteditdatum)
        val datum: TextView = view.findViewById(R.id.date_text_view)
        val kategorie: EditText = view.findViewById(R.id.texteditkategorie)
        //val waehrung : EditText = view.findViewById(R.id.waehrung_spinner)
        val waehrungSpinner = view.findViewById<Spinner>(R.id.waehrung_spinner)
        val sqLiteManager = SQLiteManager.instanceOfDatabase(requireContext())
        val insertbtn = view.findViewById<Button>(R.id.buttonsave)
        val li: LayoutInflater = layoutInflater
        val layout: View = li.inflate(R.layout.toast_erfolgreich, view.findViewById(R.id.toast))
        val toast: Toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.TOP, 10, 0)
        toast.view = layout

        fun showDatePickerDialog() {
            // Get the current date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a new DatePickerDialog and show it
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // When the date is selected, update the dateTextView with the selected date
                    datum.text = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        datum.setOnClickListener {
            showDatePickerDialog()
        }


        val waehrungAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.waehrung,
            android.R.layout.simple_spinner_item
        )
        waehrungAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        waehrungSpinner.adapter = waehrungAdapter
        waehrungSpinner.setSelection(0) // Select the first item in the list by default


        insertbtn.setOnClickListener {
            //val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
            val nametxt = name.text.toString()
            val betragtxt = getFloat(betrag.text.toString())
            val datumtxt = if(datum.text.toString().isEmpty()) getCurrentDate() else datum.text.toString()
            val kategorietxt = kategorie.text.toString()
            //var waehrungtxt = waehrung.text.toString()
            val waehrung = if(waehrungSpinner.selectedItem.toString().isEmpty()) "€" else waehrungSpinner.selectedItem.toString()
            val id : Int = sqLiteManager!!.getIdFromCounter() + 1
            //var changes: Boolean = true
            //while (changes) {
            //set default währung
           if (nametxt.length < 2) {
                //checks if name is to short
                name.setError("Name ist zu kurz -> min zeichen 2 !!!")
            } else if (nametxt.isEmpty()) {
                //checks if name is empty
                name.error = "Feld muss gefüllt !!!"
            } else if (betrag.text.toString().isEmpty()) {
                //checks if betrag is empty
                betrag.error = "Feld muss gefüllt !!!"
            } else {
                //wenn alles oben abgearbeitet wurde while loop verlassen
                //changes = false
                //neuen eintrag erstellen
                val newEintrag =
                    Eintrag(id, nametxt, betragtxt,
                        getDateFromString(datumtxt), kategorietxt, waehrung)
                //eintrag in die database inserten
                val checkinsertdata = sqLiteManager.addEintragToDatabase(newEintrag)
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

    }

    fun getCurrentDate(): String{
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy")
        val currentDate = Date()
        return dateFormat.format(currentDate)
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

    fun getDateFromString(dateString: String): Date {

        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val newsdate = sdf.parse(dateString)
        val sqlStartDate = java.sql.Date(newsdate.time)
        return sqlStartDate

    }

    private fun getStringFromDate(date: Date?): String? {
        return if (date == null) null else SQLiteManager.dateFormat.format(date)
    }


}

