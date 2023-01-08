package com.example.aaaaaaaaaaaaa.ui.main.singleitems

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.example.aaaaaaaaaaaaa.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class StatistikFragment : Fragment(R.layout.fragment_statistik) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val piechart: PieChart = requireView().findViewById(R.id.pieChart)
        val visitors: ArrayList<PieEntry> = ArrayList()
        visitors.add(PieEntry(500F, "Einnahmen"))
        visitors.add(PieEntry(1500F, "Ausgaben"))
        val pieDataSet : PieDataSet = PieDataSet(visitors, "")
//        val colorList : MutableList<Int> = ArrayList<Int>()
//        colorList.add(0,200)
//        colorList.add(1,230)
//        colorList.add(2,130)
//        colorList.add(3,230)
//
//
//        pieDataSet.setColors(colorList)
        //pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK)
        pieDataSet.setValueTextSize(16f)

        val pieData = PieData(pieDataSet)
        piechart.setData(pieData)
        piechart.getDescription().setEnabled(false)
        //piechart.setCenterText("Einnahmen")
        piechart.animate()
    }


}