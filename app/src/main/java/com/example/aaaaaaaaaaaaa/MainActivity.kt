package com.example.aaaaaaaaaaaaa


import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aaaaaaaaaaaaa.ui.main.MainFragment
import com.example.aaaaaaaaaaaaa.ui.main.menues.SideeingeklapptFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val MainFragment = MainFragment()
        val  DB : ConnectionHelper = ConnectionHelper(this)
        //hier nachfragen, ob es wieder zum  startbildschirm bringen sol oder nicht
        //dann kann man das if  else removen
        var isopened : Boolean = false
        val SideeingeklapptFragment = SideeingeklapptFragment()

        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment,"Main").commitNow()


        val buttonClick = findViewById<ImageButton>(R.id.menubtn)
        buttonClick.setOnClickListener {
            if(!isopened) {
                isopened = true
                supportFragmentManager.beginTransaction().replace(R.id.container, SideeingeklapptFragment,"Side Menu").commitNow()
            } else {
                isopened = false
                supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment,"Side Menu").commitNow()
            }



        }
    }
}