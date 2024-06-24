package com.example.ephonechallengecbe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate

class SplashScreen : AppCompatActivity() {

    private val splashTimeOut: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        val imageView: ImageView = findViewById(R.id.splash)

        val fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        fadeIn.duration = 1800
        fadeIn.start()


        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashTimeOut)
    }
}
