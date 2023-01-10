import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.aaaaaaaaaaaaa.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import kotlin.collections.ArrayList


class StatistikFragment : Fragment(R.layout.fragment_statistik) {
    private lateinit var datumVon : TextView
    private lateinit var datumBis : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        datumVon.setOnClickListener {
            showDatePickerDialogVon()
            //eintragAdapter.notifyDataSetChanged()
        }
        datumBis.setOnClickListener {
            showDatePickerDialogeBis()
            //eintragAdapter.notifyDataSetChanged()
        }
        val piechart: PieChart = requireView().findViewById(R.id.pieChart)
        val visitors: ArrayList<PieEntry> = ArrayList()
        visitors.add(PieEntry(500F, "Einnahmen"))
        visitors.add(PieEntry(1500F, "Ausgaben"))
        val pieDataSet : PieDataSet = PieDataSet(visitors, "")
        pieDataSet.setColors(intArrayOf(android.R.color.holo_green_dark, android.R.color.holo_red_dark),context);
        pieDataSet.setValueTextColor(Color.BLACK)
        pieDataSet.setValueTextSize(16f)

        val pieData = PieData(pieDataSet)
        piechart.setData(pieData)
        piechart.getDescription().setEnabled(false)
        //piechart.setCenterText("Einnahmen")
        piechart.animate()
    }
    private fun initWidgets() {
        datumVon= requireView().findViewById(R.id.datumvom)
        datumBis = requireView().findViewById(R.id.datumbis)
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
                datumVon.text = "$selectedDay.${selectedMonth + 1}.$selectedYear"
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
                datumBis.text = "$selectedDay.${selectedMonth + 1}.$selectedYear"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
        //eintragAdapter.notifyDataSetChanged()
    }


}