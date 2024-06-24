package com.example.ephonechallengecbe.data

class SmsParser {
    fun parse(smsBody: String, timestamp: Long): ParsedSmsDataClass {
        val amountRegex = """ETB ([\d,]+(?:\.\d{2})?)""".toRegex()
        val balanceRegex = """Balance is ETB ([\d,]+(?:\.\d{2})?)""".toRegex()

        // Find the first occurrence of amount
        val amountMatch = amountRegex.find(smsBody)
        val amount = amountMatch?.groupValues?.get(1)?.replace(",", "")?.toDoubleOrNull() ?: 0.0

        // Find the first occurrence of balance
        val balanceMatch = balanceRegex.find(smsBody)
        val balance = balanceMatch?.groupValues?.get(1)?.replace(",", "")?.toDoubleOrNull() ?: 0.0

        val debitOrCredit = if (smsBody.contains("debited", ignoreCase = true)) "debit" else "credit"

        return ParsedSmsDataClass(
            bankName = "Commercial Bank of Ethiopia",
            debitOrCredit = debitOrCredit,
            amount = amount,
            remainingBalance = balance,
            timestamp = timestamp
        )
    }
}
