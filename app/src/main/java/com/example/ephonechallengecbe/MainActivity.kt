package com.example.ephonechallengecbe

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.Manifest
import androidx.navigation.ui.setupWithNavController


import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.ephonechallengecbe.data.SmsRepository
import com.example.ephonechallengecbe.model.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var smsRepository: SmsRepository
    private lateinit var smsObserver: ContentObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        smsRepository = SmsRepository(applicationContext)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_SMS),
                SMS_PERMISSION_CODE
            )
        } else {
            loadSmsData()
        }

        setupNavigation()
        setupSmsObserver()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setupWithNavController(navController)
    }

    private fun loadSmsData() {
        viewModel.loadSmsData(smsRepository)
    }

    private fun setupSmsObserver() {
        smsObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)
                loadSmsData() // Reload SMS data when a change is detected
            }
        }
        contentResolver.registerContentObserver(
            Uri.parse("content://sms/"),
            true,
            smsObserver
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        contentResolver.unregisterContentObserver(smsObserver)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadSmsData()
            setupSmsObserver() // Setup observer after permission is granted
        }
    }

    companion object {
        private const val SMS_PERMISSION_CODE = 100
    }
}
