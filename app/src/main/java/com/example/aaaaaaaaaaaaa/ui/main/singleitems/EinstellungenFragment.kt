package com.example.aaaaaaaaaaaaa.ui.main.singleitems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.aaaaaaaaaaaaa.ConnectionHelper
import com.example.aaaaaaaaaaaaa.R

class EinstellungenFragment : Fragment(R.layout.fragment_einstellungen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val DB : ConnectionHelper = ConnectionHelper(activity)
        val btndbwipe = view.findViewById<Button>(R.id.Databasewipe)
        btndbwipe.setOnClickListener{
            Toast.makeText(activity,"delete",Toast.LENGTH_SHORT).show()

            //DB.clear()
        }
    }

}