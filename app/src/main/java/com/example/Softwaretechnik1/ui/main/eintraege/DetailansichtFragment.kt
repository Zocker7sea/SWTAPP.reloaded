package com.example.Softwaretechnik1.ui.main.eintraege

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.Softwaretechnik1.R
import com.example.Softwaretechnik1.SQLiteManager
import java.text.ParseException
import java.text.SimpleDateFormat


class DetailansichtFragment : Fragment(R.layout.fragment_detailansicht) {

    //private  var idd : Int = NULL as Int
    //private lateinit var name : String
    //private var betrag : Float = NULL as Float
    //private lateinit var date : Date


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val name = view.findViewById<TextView>(R.id.nameEditText)
        val betrag = view.findViewById<TextView>(R.id.betragEditText)
        val datum = view.findViewById<TextView>(R.id.datumEditText)
        val kategorie = view.findViewById<TextView>(R.id.kategorieEditText)
        val waehrung = view.findViewById<TextView>(R.id.waehrungEditText)
        val namee: String by lazy { arguments?.getString("name") ?: "Name"}
        val betragg: String by lazy { arguments?.getString("betrag") ?: "Betrag"}
        val datumm: String by lazy { arguments?.getString("date") ?: "Datum"}


        val kategoriee: String by lazy { arguments?.getString("kategorie") ?: "Kategorie"}
        val waehrungg: String by lazy { arguments?.getString("waehrung") ?: "Währung"}
        name.text = namee
        betrag.text = betragg
        datum.text = datumm
        kategorie.text = kategoriee
        waehrung.text = waehrungg

    }
    private fun getDateFromString(string: String): java.util.Date? {
        return try {
            SQLiteManager.dateFormat.parse(string)
        } catch (e: ParseException) {
            null
        } catch (e: NullPointerException) {
            null
        }
    }

    companion object {
        fun newInstance(name: String, betrag: String,datum : String,kategorie : String, waehrung: String) : DetailansichtFragment {
            return DetailansichtFragment().apply {
                arguments = Bundle().apply {
                    putString("name", name)
                    putString("betrag", betrag)
                    putString("date", datum)
                    putString("kategorie",kategorie)
                    putString("waehrung", waehrung)
                }
            }
        }
    }
}

