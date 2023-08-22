package com.startup.tugas_3_part_2_eureka

import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.startup.tugas_3_part_2_eureka.databinding.ActivityMainBinding
import com.startup.tugas_3_part_2_eureka.databinding.FragmentLoginBinding

class MainActivity : AppCompatActivity(),Communicator {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val login_fragment = loginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,login_fragment).commit()
    }

    override fun passingData(nama: String, email: String, jurusan: String, semester: String) {
        val bundle = Bundle()

        bundle.putString("nama",nama)
        bundle.putString("email",email)
        bundle.putString("jurusan",jurusan)
        bundle.putString("semester",semester)

        val home_fragment = homeFragment()

        home_fragment.arguments=bundle

        this.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,home_fragment).commit()

    }
}