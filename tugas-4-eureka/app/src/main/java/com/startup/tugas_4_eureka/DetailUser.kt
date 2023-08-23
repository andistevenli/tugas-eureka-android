package com.startup.tugas_4_eureka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.startup.tugas_4_eureka.databinding.ActivityDetailUserBinding

class DetailUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var tlFollowingFollower: TabLayout
    private lateinit var vpFollowingFollower: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViewPager(){
        binding.vpFollowingFollower.adapter = ViewPagerAdapter(supportFragmentManager)
        tlFollowingFollower.setupWithViewPager(binding.vpFollowingFollower)
    }
}