package com.startup.tugas_4_eureka.adapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.startup.tugas_4_eureka.screens.fragments.follower.FollowerFragment
import com.startup.tugas_4_eureka.screens.fragments.following.FollowingFragment

class ViewPagerAdapter(activity: AppCompatActivity
) : FragmentStateAdapter(activity) {

    var username: String? = null

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val followerFragment = FollowerFragment()
        val followingFragment = FollowingFragment()
        val bundle = Bundle()
        bundle.putString("username", username)
        return if (position == 0){
            followerFragment.arguments = bundle
            followerFragment
        } else {
            followingFragment.arguments = bundle
            followingFragment
        }
    }
}