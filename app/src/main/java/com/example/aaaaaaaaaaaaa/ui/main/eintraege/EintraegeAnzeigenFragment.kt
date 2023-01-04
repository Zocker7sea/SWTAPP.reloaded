package com.example.aaaaaaaaaaaaa.ui.main.eintraege

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
import java.sql.Date
import java.text.SimpleDateFormat


//@Entity
class EintraegeAnzeigenFragment : Fragment(R.layout.fragment_eintraege_anzeigen) {
    private var eintragListView : ListView? = null
    //var empty_imageview: ImageView? = null
    //var no_data: TextView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        loadFromDBToMemory()
        setEintragAdapter()
        setOnClickListener()

    }
private fun initWidgets() {
    eintragListView = requireView().findViewById(R.id.eintraegeListView)
   // empty_imageview = requireView().findViewById(R.id.empty_imageview)
    //no_data = requireView().findViewById<TextView>(R.id.no_data)
}

    private fun loadFromDBToMemory() {
        val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
        sqLiteManager!!.populateEintragListArray()
    }


    private fun setEintragAdapter() {
        val eintragAdapter = EintragAdapter(context, Eintrag.nonDeletedEintrag())
        eintragListView!!.adapter = eintragAdapter
    }

    private fun setOnClickListener() {
val sqLiteManager = SQLiteManager.instanceOfDatabase(context)

        val sdf = SimpleDateFormat("dd.MM.yyyy")
        eintragListView!!.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                val get =  adapterView.getAdapter().getItem(position) as Eintrag;
                val name = get.name
                val betrag =  get.betrag
                val datum = get.date
                val kategorie = sqLiteManager!!.getKategorieForNameETC(name,betrag,datum)
                val waehrung = sqLiteManager.getWaehrunFromNameETC(name,betrag,datum)
                val newDatum = sdf.format(datum)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, DetailansichtFragment.newInstance(name,betrag,newDatum.toString(),kategorie,waehrung))?.commitNow()
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

        //setEintragAdapter()

    }

}