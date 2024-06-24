package com.example.ephonechallengecbe.ui

import android.icu.text.SimpleDateFormat
import com.example.ephonechallengecbe.data.ParsedSmsDataClass
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ephonechallengecbe.model.MainViewModel
import com.example.ephonechallengecbe.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.util.Date
import java.util.Locale

class BalanceChartFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var lineChart: LineChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_balance_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        lineChart = view.findViewById(R.id.line_chart)

        viewModel.smsData.observe(viewLifecycleOwner) { smsData ->
            updateChart(smsData)
        }
    }

    private fun updateChart(smsData: List<ParsedSmsDataClass>) {
        // Reverse the order of the data to show the most recent entries first
        val entries = smsData.asReversed().mapIndexed { index, sms ->
            Entry(index.toFloat(), sms.remainingBalance.toFloat())
        }

        val dataSet = LineDataSet(entries, "Balance")
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.chart_line_color)
        dataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.chart_value_text_color)
        dataSet.lineWidth = 2f
        dataSet.setDrawValues(false) 
        dataSet.setDrawCircles(false)

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        // Format the x-axis to show dates
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dates = smsData.asReversed().map { sms ->
            dateFormat.format(Date(sms.timestamp))
        }

        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(dates)
        lineChart.xAxis.granularity = 1f
        lineChart.xAxis.labelRotationAngle = -45f

        lineChart.description.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)
        lineChart.invalidate()
    }



}
