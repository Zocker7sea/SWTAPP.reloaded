package com.example.Softwaretechnik1.ui.main.singleitems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.Softwaretechnik1.R
import com.example.Softwaretechnik1.SQLiteManager

class EinstellungenFragment : Fragment(R.layout.fragment_einstellungen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val DB  = SQLiteManager(activity)
        val btndbwipe = view.findViewById<Button>(R.id.Databasewipe)
        btndbwipe.setOnClickListener{
            Toast.makeText(activity,"delete",Toast.LENGTH_SHORT).show()
            DB.clear()
        }
    }

}