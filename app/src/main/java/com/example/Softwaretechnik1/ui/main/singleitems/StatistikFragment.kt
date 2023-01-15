import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.Softwaretechnik1.R
import com.example.Softwaretechnik1.SQLiteManager
import com.example.Softwaretechnik1.ui.main.menues.SideuebersichtFragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class StatistikFragment : Fragment(R.layout.fragment_statistik) {
    private lateinit var datefrom: TextView
    private lateinit var dateupto: TextView
    private lateinit var sqLiteManager : SQLiteManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        datefrom.setOnClickListener {
            showDatePickerDialogVon()
        }
        dateupto.setOnClickListener{
            showDatePickerDialogeBis()
        }

        val piechart: PieChart = requireView().findViewById(R.id.pieChart)
        val visitors: ArrayList<PieEntry> = ArrayList()
        val db = SQLiteManager.instanceOfDatabase(requireContext())
        val cursor=db!!.readableDatabase.rawQuery("SELECT SUM(betrag) FROM Eintrag",null)
        val cursor1= db.readableDatabase.rawQuery("SELECT SUM(betrag) FROM Ausgabe",null)
        cursor.moveToFirst()
        cursor1.moveToFirst()
        var sum1 = db.getEinnahmen()//    cursor.getFloat(0)
        var sum2 = db.getAusgaben()//   cursor1.getFloat(0)
        cursor.close()
        cursor1.close()
        visitors.add(PieEntry(sum1, "Einnahmen"))
        visitors.add(PieEntry(sum2, "Ausgaben"))
        val pieDataSet: PieDataSet = PieDataSet(visitors, "")
        pieDataSet.setColors(
            intArrayOf(
                android.R.color.holo_green_dark,
                android.R.color.holo_red_dark
            ), context
        );
        pieDataSet.setValueTextColor(Color.BLACK)
        pieDataSet.setValueTextSize(16f)

        val pieData = PieData(pieDataSet)
        piechart.setData(pieData)
        piechart.getDescription().setEnabled(false)
        //piechart.setCenterText("Einnahmen")
        piechart.animate()
        val zeitraumaendern : Button = requireView().findViewById(R.id.zeitraumaendern)
        zeitraumaendern.setOnClickListener {
            if(!datefrom.text.isEmpty() && !dateupto.text.isEmpty()) {
                sum1 = sqLiteManager.getEinnahmenVonBis(von = convertDate(datefrom.text.toString()), bis = convertDate(dateupto.text.toString()))
                sum2 = sqLiteManager.getAusgabenVonBis(von = convertDate(datefrom.text.toString()), bis = convertDate(dateupto.text.toString()))
            } else if(datefrom.text.isEmpty() && !dateupto.text.isEmpty()) {
                sum1 = sqLiteManager.getEinnahmenBis(bis = convertDate(dateupto.text.toString()))
                sum2 = sqLiteManager.getAusgabenBis(bis = convertDate(dateupto.text.toString()))
            } else if(!datefrom.text.isEmpty() && dateupto.text.isEmpty()) {
                sum1 = sqLiteManager.getEinnahmenVon(von = convertDate(datefrom.text.toString()))
                sum2 = sqLiteManager.getAusgabenVon(von = convertDate(datefrom.text.toString()))
            } else {
                println("both empty")
                sum1 = sqLiteManager.getEinnahmen()
                sum2 = sqLiteManager.getAusgaben()
            }
            piechart.clear()
            visitors.clear()
            visitors.add(PieEntry(sum1, "Einnahmen"))
            visitors.add(PieEntry(sum2, "Ausgaben"))
            val pieDataSet: PieDataSet = PieDataSet(visitors, "")
            pieDataSet.setColors(
                intArrayOf(
                    android.R.color.holo_green_dark,
                    android.R.color.holo_red_dark
                ), context
            );
            pieDataSet.setValueTextColor(Color.BLACK)
            pieDataSet.setValueTextSize(16f)

            val pieData = PieData(pieDataSet)
            piechart.setData(pieData)
            piechart.getDescription().setEnabled(false)
            //piechart.setCenterText("Einnahmen")
            piechart.animate()
        }
    }

    private fun initWidgets() {
        datefrom = requireView().findViewById(R.id.datumvom)
        dateupto = requireView().findViewById(R.id.datumbis)
        sqLiteManager = SQLiteManager.instanceOfDatabase(context)!!
    }

    fun convertDate(date: String): String {
        val dateParts = date.split("-")
        val year = String.format("%4d", dateParts[0].toInt())
        val month = String.format("%02d", dateParts[1].toInt())
        val day = String.format("%02d", dateParts[2].toInt())
        return "$year-$month-$day"
    }

    fun showDatePickerDialogVon() {
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
                datefrom.text = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                datefrom.text = convertDate(datefrom.text.toString())
            },
            year,
            month,
            day
        )
        datePickerDialog.show()

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
                dateupto.text = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                dateupto.text = convertDate(dateupto.text.toString())
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
        //eintragAdapter.notifyDataSetChanged()
    }
}