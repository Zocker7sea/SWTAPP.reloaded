package com.example.aaaaaaaaaaaaa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.aaaaaaaaaaaaa.Model.Eintrag
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat

class EintragAdapter(context: Context?, eintrags: List<Eintrag?>?) :
    ArrayAdapter<Eintrag?>(context!!, 0, eintrags!!) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row  : View
        if (convertView == null)
            row = LayoutInflater.from(context).inflate(R.layout.eintrag_cell, parent, false)
        else
            row = convertView

        //den eintrag an der listview postition getten
        val eintrag = getItem(position)
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val id = row.findViewById<TextView>(R.id.cellid)
        val name = row.findViewById<TextView>(R.id.cellName)
        val betrag = row.findViewById<TextView>(R.id.cellBetrag)
        val datum = row.findViewById<TextView>(R.id.cellDatum)
        id.text = eintrag!!.getId().toString()
        name.text = eintrag.getName()
        betrag.text = eintrag.getBetrag().toString()
        datum.text =     sdf.format(eintrag.getDate())       //getDateFromString(eintrag.date.toString()).toString()
        return row
    }
     override fun getItem(position: Int): Eintrag? {
        return super.getItem(position)
    }
}