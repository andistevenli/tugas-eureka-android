package com.startup.bukuku.screen.activities.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.startup.bukuku.databinding.ActivitySplashScreenBinding
import com.startup.bukuku.screen.activities.daftar_buku.DaftarBukuActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var bindingSplashScreen: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplashScreen = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(bindingSplashScreen.root)

        bindingSplashScreen.ivLogo.alpha = 0f
        bindingSplashScreen.ivLogo.animate().setDuration(3000).alpha(1f).withEndAction {
            val intentToMainActivity = Intent(this, DaftarBukuActivity::class.java)
            startActivity(intentToMainActivity)
        }
    }
}