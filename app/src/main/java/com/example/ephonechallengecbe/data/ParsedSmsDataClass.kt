package com.example.ephonechallengecbe.data

data class ParsedSmsDataClass(
    val bankName: String,
    val debitOrCredit: String,
    val amount: Double,
    val remainingBalance: Double,
    val timestamp: Long
)
