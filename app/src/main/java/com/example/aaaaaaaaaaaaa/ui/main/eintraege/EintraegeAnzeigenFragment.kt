package com.example.aaaaaaaaaaaaa.ui.main.eintraege

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaaaaaaaaaaaa.*
import com.example.aaaaaaaaaaaaa.Model.Eintrag
import java.sql.Date
import java.text.ParseException


//@Entity
class EintraegeAnzeigenFragment : Fragment(R.layout.fragment_eintraege_anzeigen) {
   // lateinit var eintragRecyclerView : RecyclerView
    //lateinit var  eintragAdapter  : EintragAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
        val db : SQLiteDatabase = sqLiteManager!!.readableDatabase
        val cursor : Cursor = sqLiteManager.getdata()
//        eintragRecyclerView = view.findViewById<RecyclerView>(R.id.eintraegeListView)
//        eintragRecyclerView.setLayoutManager(LinearLayoutManager(context));



       // eintragAdapter = EintragAdapter(context,R.layout.eintrag_cell)
        var id : Int
        var name  : String
        var betrag  : Float
        var datum : Date
        while(cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndexOrThrow("Counter"));
            name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            betrag = cursor.getFloat(cursor.getColumnIndexOrThrow("betrag"))
            datum = getDateFromString(cursor.getString(cursor.getColumnIndexOrThrow("datum"))) as Date
            val newEintrag : Eintrag = Eintrag(id,name,betrag,getStringFromDate(datum))
        }




//
//        val todoAdapter = EintragCursorAdapter(context, cursor)
//       // val eintragCursorAdapter : EintragCursorAdapter = EintragCursorAdapter(context,cursor)
//        eintragListView.adapter = todoAdapter
//



//        val idlist = sqLiteManager!!.getListViewItemsId()
//        val namelist = sqLiteManager.getListViewItemsName()
//        val betraglist = sqLiteManager.getListViewItemsBetrag()
//        val datumlist = sqLiteManager.getListViewItemsDatum()
//        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_2,idlist)
//        eintragListView.adapter = adapter


//        val c : Cursor = sqLiteManager!!.getdata()
//        val theList: ArrayList<String> = ArrayList()
//        if(c.count == 0) {
//            Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()
//        } else {
//            while(c.moveToNext()) {
//                theList.add(c.getString(1))
//                val listAdapter: ListAdapter = ArrayAdapter<Any?>(requireContext(),R.layout.eintrag_cell,
//                    theList as List<Any?>
//                )// android.R.layout.simple_list_item_1, theList)
//                eintragListView.setAdapter(listAdapter)
//            }
//        }






        //initWidgets()

    // val sqLiteManager = SQLiteManager(requireContext())
        //sqLiteManager.populateEintragListArray()
        //loadFromDBToMemory()
        //val eintragAdapter = EintragAdapter(requireContext(), Eintrag.nonDeletedEintrag())
        //eintragListView!!.adapter = eintragAdapter
        //setEintragAdapter()
    }
//    private fun initWidgets() {
//        eintragListView = view?.findViewById<ListView>(R.id.eintraegeListView)
//    }

//    private fun setEintragAdapter() {
//        val eintragAdapter = EintragAdapter(requireContext(), Eintrag.nonDeletedEintrag())
//        eintragListView!!.adapter = eintragAdapter
//    }
//    private fun loadFromDBToMemory() {
//       val sqLiteManager = SQLiteManager.instanceOfDatabase(context)
//        if (sqLiteManager != null) {
//            sqLiteManager.populateEintragListArray()
//        }
//    }
fun onBackgroundUpdate(value : Eintrag) {
    //eintragAdapter.add(value)
}

    override fun onResume() {
        super.onResume()
        //setEintragAdapter()
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
    private fun getStringFromDate(date: java.util.Date?): String? {
        return if (date == null) null else SQLiteManager.dateFormat.format(date)
    }

}