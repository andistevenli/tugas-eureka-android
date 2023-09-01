package com.startup.tugas_4_eureka.screens.fragments.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_4_eureka.model.GithubUsers
import com.startup.tugas_4_eureka.repository.Hasil
import com.startup.tugas_4_eureka.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor (private val repository: Repository): ViewModel() {
    fun getGithubDetailUserFollowing(username: String) : LiveData<Hasil<List<GithubUsers>>>{
        return  repository.getGithubDetailUserFollowing(username)
    }
}