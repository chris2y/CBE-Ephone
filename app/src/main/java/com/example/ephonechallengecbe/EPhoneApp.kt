package com.example.ephonechallengecbe

import android.app.Application
import com.example.ephonechallengecbe.data.SmsRepository

class EPhoneApp : Application() {
    lateinit var smsRepository: SmsRepository
        private set

    override fun onCreate() {
        super.onCreate()
        smsRepository = SmsRepository(applicationContext)
    }

}