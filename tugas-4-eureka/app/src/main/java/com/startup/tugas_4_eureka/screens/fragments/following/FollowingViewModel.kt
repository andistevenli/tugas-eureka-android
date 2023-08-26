package com.startup.tugas_4_eureka.screens.fragments.following

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_4_eureka.model.GithubUsers
import com.startup.tugas_4_eureka.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FollowingViewModel(private val repository: Repository): ViewModel() {
    val liveData: MutableLiveData<Response<List<GithubUsers>>> = MutableLiveData()

    fun getGithubDetailUserFollowing(username: String){
        viewModelScope.launch {
            val response = repository.getGithubDetailUserFollowing(username)
            liveData.value = response
        }
    }
}