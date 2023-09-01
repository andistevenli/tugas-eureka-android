package com.startup.tugas_5_eureka.ui.activities.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.startup.tugas_5_eureka.R
import com.startup.tugas_5_eureka.databinding.ActivitySplashScreenBinding
import com.startup.tugas_5_eureka.ui.activities.daftar_buku.DaftarBukuActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivLogo.alpha = 0f
        binding.ivLogo.animate().setDuration(3000).alpha(1f).withEndAction {
            val intentToMainActivity = Intent(this, DaftarBukuActivity::class.java)
            startActivity(intentToMainActivity)
        }
    }
}