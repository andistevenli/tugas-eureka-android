package com.startup.tugas_4_eureka.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startup.tugas_4_eureka.repository.Repository
import com.startup.tugas_4_eureka.screens.activities.detail_user.DetailUserViewModel
import com.startup.tugas_4_eureka.screens.activities.main.MainViewModel
import com.startup.tugas_4_eureka.screens.fragments.follower.FollowerFragment
import com.startup.tugas_4_eureka.screens.fragments.follower.FollowerViewModel
import com.startup.tugas_4_eureka.screens.fragments.following.FollowingFragment
import com.startup.tugas_4_eureka.screens.fragments.following.FollowingViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailUserViewModel::class.java)){
            return DetailUserViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FollowerViewModel::class.java)){
            return FollowerViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FollowingViewModel::class.java)){
            return FollowingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}