package com.example.Softwaretechnik1.ui.main.ausgaben

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.Softwaretechnik1.Model.Eintrag
import com.example.Softwaretechnik1.R
import com.example.Softwaretechnik1.SQLiteManager
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AusgabenhinzufuegenFragment : Fragment(R.layout.fragment_ausgabenhinzufuegen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //edit felder und button
        val name: EditText = view.findViewById(R.id.name)
        val betrag: EditText = view.findViewById(R.id.amount)
        //val datum : EditText = view.findViewById(R.id.texteditdatum)
        val datum: TextView = view.findViewById(R.id.date)
        val kategorie: EditText = view.findViewById(R.id.cat)
        // val waehrung : EditText = view.findViewById(R.id.waehrung_spinner)
        //  val waehrungSpinner = view.findViewById<Spinner>(R.id.waehrung_spinner)

        val sqLiteManager = SQLiteManager.instanceOfDatabase(requireContext())
        val insertbtn = view.findViewById<Button>(R.id.bestätigen)
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
                    datum.text = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    datum.text = convertDate(datum.text.toString())
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


        insertbtn.setOnClickListener {
            //val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
            val nametxt = name.text.toString()
            val betragtxt = getFloat(betrag.text.toString())
            val datumtxt = if(datum.text.toString().isEmpty()) getCurrentDate() else datum.text.toString()
            val kategorietxt = kategorie.text.toString()
            //var waehrungtxt = waehrung.text.toString()
            val waehrung =  "Euro"
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
            } else if (datumtxt.isEmpty())  {
                datum.error = "Datum muss gefüllt sein !!!"

            } else {
                //wenn alles oben abgearbeitet wurde while loop verlassen
                //changes = false
                //neuen eintrag erstellen
                val newEintrag =
                    Eintrag( id,nametxt, betragtxt,
                        getDateFromString(datumtxt), kategorietxt, waehrung)
                //eintrag in die database inserten
                val checkinsertdata = sqLiteManager.addAusgabetoDatabase(newEintrag)
                if (checkinsertdata) {
                    //wenn es geklappt hat, custom toast aufrufen und den eintrag in die eintrasliste hinzufuegen
                    toast.show()
                    //Eintrag.eintragArrayList.add(newEintrag)
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
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
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

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val newsdate = sdf.parse(dateString)
        val sqlStartDate = java.sql.Date(newsdate.time)
        return sqlStartDate

    }
    private fun convertDate(date: String): String {
        val dateParts = date.split("-")
        val year =  String.format("%4d", dateParts[0].toInt())
        val month = String.format("%02d", dateParts[1].toInt())
        val day = String.format("%02d", dateParts[2].toInt())
        return "$year-$month-$day"
    }

    private fun getStringFromDate(date: Date?): String? {
        return if (date == null) null else SQLiteManager.dateFormat.format(date)
    }



}