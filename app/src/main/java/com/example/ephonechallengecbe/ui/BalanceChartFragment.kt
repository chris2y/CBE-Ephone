package com.example.ephonechallengecbe.ui

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
        dataSet.valueTextColor = ContextCompat.getColor(requireContext(),
            R.color.chart_value_text_color
        ) // Customize value text color
        dataSet.lineWidth = 2f
        dataSet.setDrawValues(false) // Optionally, turn off value labels on the chart
        dataSet.setDrawCircles(false) // Optionally, turn off data point circles

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.description.isEnabled = false // Disable description label
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)
        lineChart.invalidate() // Refresh the chart
    }


}
