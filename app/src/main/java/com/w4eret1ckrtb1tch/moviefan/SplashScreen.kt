package com.W4ereT1ckRtB1tch.moviefan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.w4eret1ckrtb1tch.moviefan.R
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
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