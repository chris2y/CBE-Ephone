package com.example.ephonechallengecbe.ui

import com.example.ephonechallengecbe.data.ParsedSmsDataClass
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ephonechallengecbe.R

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ParsedSmsAdapter : ListAdapter<ParsedSmsDataClass, ParsedSmsAdapter.ViewHolder>(
    ParsedSmsDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parsed_sms, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sms = getItem(position)
        holder.bind(sms)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.date_text_view)
        private val bankNameTextView: TextView = itemView.findViewById(R.id.bank_name_text_view)
        private val timeTextView: TextView = itemView.findViewById(R.id.time_text_view)
        private val debitCreditTextView: TextView = itemView.findViewById(R.id.debit_credit_text_view)
        private val amountTextView: TextView = itemView.findViewById(R.id.amount_text_view)
        private val balanceTextView: TextView = itemView.findViewById(R.id.balance_text_view)

        fun bind(sms: ParsedSmsDataClass) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = Date(sms.timestamp)
            dateTextView.text = "Date: ${dateFormat.format(date)}"
            bankNameTextView.text = "Bank: ${sms.bankName}"
            timeTextView.text = "Time: ${formatTimestampToTime(sms.timestamp)}"
            val typeLabel = "Type: "
            val typeValue = sms.debitOrCredit

            debitCreditTextView.text = buildSpannedString {
                append(typeLabel)
                color(if (sms.debitOrCredit == "debit")
                    itemView.context.getColor(R.color.red)
                else
                    itemView.context.getColor(R.color.green)) {
                    append(typeValue)
                }
            }
            amountTextView.text = "Amount: ${sms.amount}"
            balanceTextView.text = "Balance: ${sms.remainingBalance}"
        }

        private fun formatTimestampToTime(timestamp: Long): String {
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val time = Date(timestamp)
            return timeFormat.format(time)
        }
    }

    class ParsedSmsDiffCallback : DiffUtil.ItemCallback<ParsedSmsDataClass>() {
        override fun areItemsTheSame(oldItem: ParsedSmsDataClass, newItem: ParsedSmsDataClass): Boolean {
            return oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(oldItem: ParsedSmsDataClass, newItem: ParsedSmsDataClass): Boolean {
            return oldItem == newItem
        }
    }
}
