package com.startup.tugas_4_eureka.screens.activities.detail_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_4_eureka.model.GithubDetailUser
import com.startup.tugas_4_eureka.repository.Hasil
import com.startup.tugas_4_eureka.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun getGithubDetailUser(username: String) : LiveData<Hasil<GithubDetailUser>> {
        return  repository.getGithubDetailUser(username)
    }
}