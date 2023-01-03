package com.example.aaaaaaaaaaaaa.ui.main.eintraege

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import com.example.aaaaaaaaaaaaa.EintragAdapter
import com.example.aaaaaaaaaaaaa.Model.Eintrag
import com.example.aaaaaaaaaaaaa.R
import com.example.aaaaaaaaaaaaa.SQLiteManager


//@Entity
class EintraegeAnzeigenFragment : Fragment(R.layout.fragment_eintraege_anzeigen) {
    private var eintragListView : ListView? = null
    var empty_imageview: ImageView? = null
    var no_data: TextView? = null
   // lateinit var eintragRecyclerView : RecyclerView
    //lateinit var  eintragAdapter  : EintragAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()

        loadFromDBToMemory()
        setEintragAdapter()
        setOnClickListener()

    }
private fun initWidgets() {
    eintragListView = requireView().findViewById(R.id.eintraegeListView)
    empty_imageview = requireView().findViewById(R.id.empty_imageview)
    no_data = requireView().findViewById<TextView>(R.id.no_data)
}

    private fun loadFromDBToMemory() {
       // if(eintragListView!!.isEmpty()) {
        //    empty_imageview!!.visibility = View.VISIBLE
       //     no_data!!.visibility = View.VISIBLE
       // } else {
            val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
            sqLiteManager!!.populateEintragListArray()

       // }
       // empty_imageview!!.visibility = View.GONE
       // no_data!!.visibility = View.GONE

    }


    private fun setEintragAdapter() {
        val eintragAdapter = EintragAdapter(context, Eintrag.nonDeletedEintrag())
        eintragListView!!.adapter = eintragAdapter
    }

    private fun setOnClickListener() {
        eintragListView!!.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, DetailansichtFragment())?.commitNow()
            }
    }

    override fun onResume() {
        super.onResume()

        //setEintragAdapter()

    }

}