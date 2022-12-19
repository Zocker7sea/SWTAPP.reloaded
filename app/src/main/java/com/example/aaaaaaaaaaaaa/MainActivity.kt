package com.example.aaaaaaaaaaaaa


import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aaaaaaaaaaaaa.ui.main.MainFragment
import com.example.aaaaaaaaaaaaa.ui.main.ausgaben.AusgabenhinzufuegenFragment
import com.example.aaaaaaaaaaaaa.ui.main.ausgaben.RegelmaessigeausgabenFragment
import com.example.aaaaaaaaaaaaa.ui.main.einnahmen.EinnahmenhinzufuegenFragment
import com.example.aaaaaaaaaaaaa.ui.main.einnahmen.RegelmaessigeeinnahmenFragment
import com.example.aaaaaaaaaaaaa.ui.main.eintraege.EintraegeAnzeigenFragment
import com.example.aaaaaaaaaaaaa.ui.main.eintraege.EintraegesuchenFragment
import com.example.aaaaaaaaaaaaa.ui.main.kategorie.KategorieanzeigenFragment
import com.example.aaaaaaaaaaaaa.ui.main.kategorie.KategorieerstellenFragment
import com.example.aaaaaaaaaaaaa.ui.main.kategorie.KategorieloeschenFragment
import com.example.aaaaaaaaaaaaa.ui.main.menues.*
import com.example.aaaaaaaaaaaaa.ui.main.singleitems.EinstellungenFragment
import com.example.aaaaaaaaaaaaa.ui.main.singleitems.StatistikFragment
import com.example.aaaaaaaaaaaaa.ui.main.singleitems.WaehrungsrechnerFragment
import com.example.aaaaaaaaaaaaa.ui.main.sparziel.SparzielanzeigenFragment
import com.example.aaaaaaaaaaaaa.ui.main.sparziel.SparzielerstellenFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val MainFragment = MainFragment()
        val SideFragemnt = SideeingeklapptFragment()
        val  DB : ConnectionHelper = ConnectionHelper(this)
        //hier nachfragen, ob es wieder zum  startbildschirm bringen sol oder nicht
        //dann kann man das if  else removen
        var isopened : Boolean = false
        val SideeingeklapptFragment = SideeingeklapptFragment()

        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment,"Main").commitNow()


//        val buttonClick = findViewById<ImageButton>(R.id.menubtn)
//        buttonClick.setOnClickListener {
//            if(!isopened) {
//                isopened = true
//                supportFragmentManager.beginTransaction().replace(R.id.container, SideeingeklapptFragment,"Side Menu").commitNow()
//            } else {
//                isopened = false
//                supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment,"Side Menu").commitNow()
//            }
//        }

    }

    fun onButtonClick(view : View) {
        val fragment : Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        //getSupportFragmentManager().findFragmentById(R.id.container);
        //main
        if (fragment is MainFragment) {
           // Toast.makeText(this,"Main",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideeingeklapptFragment()).commitNow()
        }
        //side menu
        else if (fragment is SideeingeklapptFragment) {
            //Toast.makeText(this,"Side",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()
        }
        //fragen, was dann passieren sollte, wenn nicht das mainFragment wieder auf gerufen werden soll
        else if (fragment is SideuebersichtFragment) {
            //Toast.makeText(this,"Side",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()
        } else if (fragment is SidekategorieFragment) {
            //Toast.makeText(this,"Side",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()
        } else if (fragment is SideeintraegeFragment) {
            //Toast.makeText(this,"Side",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()
        } else if (fragment is SidesparzielFragment) {
            //Toast.makeText(this,"Side",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()
        }
        //Einnahmen
        else if(fragment is EinnahmenhinzufuegenFragment) {
            //Toast.makeText(this,"Einnahmen hinzufuegen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideeingeklapptFragment()).commitNow()
        } else if(fragment is RegelmaessigeeinnahmenFragment) {
            //Toast.makeText(this,"Regelmasessigeeinnahmen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideuebersichtFragment(),"Side Menu").commitNow()
        }
        //Ausgaben
        else if(fragment is AusgabenhinzufuegenFragment) {
            //Toast.makeText(this,"Ausgaben hinzufuegen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideeingeklapptFragment()).commitNow()
        } else if(fragment is RegelmaessigeausgabenFragment) {
            //Toast.makeText(this,"Regelmasessigeeinnahmen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideuebersichtFragment(),"Side Menu").commitNow()
        }
        //eintraege
        else if(fragment is EintraegeAnzeigenFragment) {
            //Toast.makeText(this,"EintraegeAnzeigenFragment",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideeintraegeFragment(),"Side Menu").commitNow()
        }else if(fragment is EintraegesuchenFragment) {
           // Toast.makeText(this,"EintraegeAnzeigenFragment",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideeintraegeFragment(),"Side Menu").commitNow()
        }
        //Kategorie
        else if(fragment is KategorieloeschenFragment) {
            //Toast.makeText(this,"Kategorie loeschen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SidekategorieFragment(),"Side Menu").commitNow()
        } else if(fragment is KategorieanzeigenFragment) {
            //Toast.makeText(this,"Kategorie anzeigen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SidekategorieFragment(),"Side Menu").commitNow()
        } else if(fragment is KategorieerstellenFragment) {
            //Toast.makeText(this,"Kategorie erstellen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SidekategorieFragment(),"Side Menu").commitNow()
        }
        //sparziel
        else if(fragment is SparzielanzeigenFragment) {
            //Toast.makeText(this,"SparzielanzeigenFragment",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SidesparzielFragment(),"Side Menu").commitNow()
        }else if(fragment is SparzielerstellenFragment) {
           // Toast.makeText(this,"SparzielerstellenFragment",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SidesparzielFragment(),"Side Menu").commitNow()
        }
        //Single Items
        else if(fragment is EinstellungenFragment) {
            //Toast.makeText(this,"Einstellungen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideeingeklapptFragment(),"Side Menu").commitNow()
        }else if(fragment is StatistikFragment) {
            //Toast.makeText(this,"Einstellungen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideeingeklapptFragment(),"Side Menu").commitNow()
        }else if(fragment is WaehrungsrechnerFragment) {
           // Toast.makeText(this,"Einstellungen",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.container, SideeingeklapptFragment(),"Side Menu").commitNow()
        }

    }
}