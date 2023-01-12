package com.example.aaaaaaaaaaaaa.ui.main.eintraege

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.aaaaaaaaaaaaa.EintragAdapter
import com.example.aaaaaaaaaaaaa.Model.Eintrag
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.SQLiteManager
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*


//@Entity
class EintraegeAnzeigenFragment : Fragment(R.layout.fragment_eintraege_anzeigen) {
    private var eintragListView: ListView? = null
    private lateinit var datumVon : TextView
    private lateinit var datumBis : TextView
    private lateinit var eintragAdapter : EintragAdapter
    private lateinit var btnmenu : ImageButton
    private lateinit var newlist: ArrayList<Eintrag>
    private lateinit var searchbtn : ImageButton
    private lateinit var sqLiteManager : SQLiteManager



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        datumVon.setOnClickListener {
            showDatePickerDialogVon()
        }
        datumBis.setOnClickListener {
            showDatePickerDialogeBis()
        }
        btnmenu.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, EintraegemenuFragment())?.commitNow()
        }
        loadFromDBToMemory()
        setEintragAdapter()
        setOnClickListener()
        searchbtn.setOnClickListener {
            if(!datumVon.text.isEmpty() && !datumBis.text.isEmpty()) {
                println("Datumvon ist " + datumVon.text.toString())
                println("Datumbis ist " + datumBis.text.toString())
                newlist = sqLiteManager.populateEintragListArrayVonBIs(von = datumVon.text.toString(), bis = datumBis.text.toString())
            } else if(datumVon.text.isEmpty() && !datumBis.text.isEmpty()) {
                newlist = sqLiteManager.populateEintragListArrayBis(bis = datumBis.text.toString())
            } else if(!datumVon.text.isEmpty() && datumBis.text.isEmpty()) {
                newlist = sqLiteManager.populateEintragListArrayVon(von = datumVon.text.toString())
            } else {
                newlist = sqLiteManager.populateEintragListArray()
            }
            setEintragAdapter()
        }

    }
        fun showDatePickerDialogVon() {

            // Get the current date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day =  calendar.get(Calendar.DAY_OF_MONTH)

            // Create a new DatePickerDialog and show it
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // When the date is selected, update the dateTextView with the selected date
                    datumVon.text = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
            //Toast.makeText(context, "what date " + datumVon.text.toString(), Toast.LENGTH_SHORT).show()
            //newlist.clear()
//            if(datumBis.text.isEmpty()) {
//                newlist = sqLiteManager!!.populateEintragListArrayVon(von = datumVon.text.toString())
//            } else {
//                newlist = sqLiteManager!!.populateEintragListArrayVonBIs(datumVon.text.toString(),datumBis.text.toString())
//            }

            //setEintragAdapter()


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
                    datumBis.text = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        //.makeText(context, "what date " + datumBis.text.toString(), Toast.LENGTH_SHORT).show()
            //newlist.clear()
//            if(datumVon.text.isEmpty()) {
//                newlist = sqLiteManager!!.populateEintragListArrayBis(bis = datumBis.text.toString())
//            } else {
//                newlist = sqLiteManager!!.populateEintragListArrayVonBIs(datumVon.text.toString(),datumBis.text.toString())
//            }
//            //newlist = sqLiteManager!!.populateEintragListArrayBis(bis = datumBis.text.toString())
//            setEintragAdapter()
        }


    private fun initWidgets() {
        eintragListView = requireView().findViewById(R.id.eintraegeListView)
        datumVon= requireView().findViewById(R.id.editTextDate)
        datumBis = requireView().findViewById(R.id.editTextDate2)
        btnmenu = requireView().findViewById(R.id.eintraegemenu)
        searchbtn = requireView().findViewById(R.id.search)
        sqLiteManager = SQLiteManager.instanceOfDatabase(context)!!
        newlist = ArrayList<Eintrag>()
    }

    private fun loadFromDBToMemory() {
            newlist = sqLiteManager.populateEintragListArray()
    }


    private fun setEintragAdapter() {
        eintragAdapter = EintragAdapter(context, newlist)
        eintragListView!!.adapter = eintragAdapter
    }

    private fun setOnClickListener() {
        val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        eintragListView!!.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                val get = eintragAdapter.getItem(position);
                val name = get!!.getName()
                val betrag = get.getBetrag()
                val datum = get.getDate()
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


    fun getDateFromString(dateString: String): Date {

        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val newsdate = sdf.parse(dateString)
        //val sqlStartDate = java.sql.Date(newsdate.time)
        return java.sql.Date(newsdate.time)

    }

    override fun onResume() {
        super.onResume()
        //newlist.clear()
        setEintragAdapter()

    }

}