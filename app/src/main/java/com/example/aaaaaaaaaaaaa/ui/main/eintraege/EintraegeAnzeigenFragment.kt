package com.example.aaaaaaaaaaaaa.ui.main.eintraege

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.aaaaaaaaaaaaa.EintragAdapter
import com.example.aaaaaaaaaaaaa.Model.Eintrag
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.SQLiteManager

import java.text.SimpleDateFormat
import java.util.*


//@Entity
class EintraegeAnzeigenFragment : Fragment(R.layout.fragment_eintraege_anzeigen) {
    private var eintragListView: ListView? = null
    private lateinit var datumVon : TextView
    private lateinit var datumBis : TextView
    private lateinit var eintragAdapter : EintragAdapter
    //var empty_imageview: ImageView? = null
    //var no_data: TextView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        datumVon.setOnClickListener {
            showDatePickerDialogVon()
            eintragAdapter.notifyDataSetChanged()
        }
        datumBis.setOnClickListener {
            showDatePickerDialogeBis()
            eintragAdapter.notifyDataSetChanged()
        }

        loadFromDBToMemory()
        setEintragAdapter()
        setOnClickListener()

    }
        fun showDatePickerDialogVon() {
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
                    datumVon!!.text = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()


        }

        fun showDatePickerDialogeBis() {
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
                    datumBis!!.text = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
            eintragAdapter.notifyDataSetChanged()

        }





    private fun initWidgets() {
        eintragListView = requireView().findViewById(R.id.eintraegeListView)
        datumVon= requireView().findViewById(R.id.editTextDate)
        datumBis = requireView().findViewById(R.id.editTextDate2)
        // empty_imageview = requireView().findViewById(R.id.empty_imageview)
        //no_data = requireView().findViewById<TextView>(R.id.no_data)
    }

    private fun loadFromDBToMemory() {
        val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
        if(datumVon.text.isEmpty() || datumBis.text.isEmpty()) {
            sqLiteManager!!.populateEintragListArray()
        } else {
            sqLiteManager!!.populateEintragListArrayVonBIs(von = datumVon.text.toString(), bis = datumBis.text.toString())
        }

    }


    private fun setEintragAdapter() {
        eintragAdapter = EintragAdapter(context, Eintrag.nonDeletedEintrag())
        eintragListView!!.adapter = eintragAdapter
    }

    private fun setOnClickListener() {
        //eintragAdapter.notifyDataSetChanged()
        val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        eintragListView!!.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                val get = adapterView.getAdapter().getItem(position) as Eintrag;
                val name = get.name
                val betrag = get.betrag
                val datum = get.date
                val kategorie = sqLiteManager!!.getKategorieForNameETC(name, betrag, datum)
                val waehrung = sqLiteManager.getWaehrunFromNameETC(name, betrag, datum)
                val newDatum = sdf.format(datum)
                eintragAdapter.notifyDataSetChanged()
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.container,
                    DetailansichtFragment.newInstance(
                        name,
                        betrag.toString(),
                        newDatum.toString(),
                        kategorie,
                        waehrung
                    )
                )?.commitNow()
            }
    }


    fun getDateFromString(dateString: String): java.util.Date {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val newsdate = sdf.parse(dateString)
        val sqlStartDate = java.sql.Date(newsdate.time)
        return sqlStartDate
    }

    override fun onResume() {
        super.onResume()
        setEintragAdapter()

    }

}