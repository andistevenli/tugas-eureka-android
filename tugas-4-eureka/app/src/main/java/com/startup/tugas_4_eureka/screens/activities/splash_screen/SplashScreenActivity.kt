package com.startup.tugas_4_eureka.screens.activities.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.startup.tugas_4_eureka.databinding.ActivitySplashScreenBinding
import com.startup.tugas_4_eureka.screens.activities.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var bindingSplashScreen: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplashScreen = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(bindingSplashScreen.root)

        bindingSplashScreen.ivLogo.alpha = 0f
        bindingSplashScreen.ivLogo.animate().setDuration(3000).alpha(1f).withEndAction {
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
        }
    }
}