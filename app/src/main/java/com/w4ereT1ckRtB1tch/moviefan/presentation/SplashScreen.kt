package com.w4ereT1ckRtB1tch.moviefan.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.ActivitySplashScreenBinding
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashScreenBinding>(
            this,
            R.layout.activity_splash_screen
        )
        startApp()
    }

    private fun startApp() {
        val intent = Intent(this, MainActivity::class.java)
        val timerStart = Timer()
        timerStart.schedule(5000) {
            startActivity(intent)
            finish()
        }
    }
}