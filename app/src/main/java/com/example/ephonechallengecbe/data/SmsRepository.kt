package com.example.ephonechallengecbe.data
import android.content.Context
import android.net.Uri
import android.util.Log

class SmsRepository(private val context: Context) {
    private val smsParser = SmsParser()

    fun getLatestSms(): List<ParsedSmsDataClass> {
        val smsList = mutableListOf<ParsedSmsDataClass>()
        val cursor = context.contentResolver.query(
            Uri.parse("content://sms/inbox"),
            null,
            "address = ?",
            arrayOf("CBE"),
            "date DESC"
        )

        cursor?.use {
            val bodyIndex = it.getColumnIndex("body")
            val dateIndex = it.getColumnIndex("date")
            val addressIndex = it.getColumnIndex("address")

            while (it.moveToNext()) {
                val smsBody = it.getString(bodyIndex)
                val timestamp = it.getLong(dateIndex)
                val address = it.getString(addressIndex)

                if (address.equals("CBE", ignoreCase = true)) {
                    try {
                        val parsedSms = smsParser.parse(smsBody, timestamp)
                        smsList.add(parsedSms)
                    } catch (e: Exception) {
                        Log.e("SmsRepository", "Error parsing SMS: ${e.message}")
                    }
                }
            }
        }

        return smsList
    }
}
