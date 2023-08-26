package com.startup.tugas_4_eureka.screens.activities.detail_user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_4_eureka.model.GithubDetailUser
import com.startup.tugas_4_eureka.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailUserViewModel(private val repository: Repository): ViewModel() {
    val liveData: MutableLiveData<Response<GithubDetailUser>> = MutableLiveData()

    fun getGithubDetailUser(username: String){
        viewModelScope.launch {
            val response = repository.getGithubDetailUser(username)
            liveData.value = response
        }
    }
}