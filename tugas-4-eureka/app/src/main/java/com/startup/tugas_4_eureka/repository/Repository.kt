package com.startup.tugas_4_eureka.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.startup.tugas_4_eureka.BuildConfig
import com.startup.tugas_4_eureka.model.GithubDetailUser
import com.startup.tugas_4_eureka.model.GithubUsers
import com.startup.tugas_4_eureka.retrofit.UsersApiClient
import retrofit2.Response

class Repository {


    fun getGithubUsers() : LiveData<Hasil<List<GithubUsers>>> = liveData {
        emit(Hasil.Loading)
        try {
            val data = UsersApiClient.api.getGithubUsers()
            if (data.isEmpty()){
                emit(Hasil.Empty)
            } else{
                emit(Hasil.Success(data))
            }
        } catch (e: Exception){
            Log.d("ANDIGITHUB",e.message.toString())
            emit(Hasil.Error(e.message.toString()))
        }
    }

    fun getGithubDetailUser(username: String) : LiveData<Hasil<GithubDetailUser>> = liveData {
        emit(Hasil.Loading)
        try {
            val data = UsersApiClient.api.getGithubDetailUser(username)
            emit(Hasil.Success(data))
        } catch (e: Exception){
            Log.d("ANDIGITHUBFOLLOWER",e.message.toString())
            emit(Hasil.Error(e.message.toString()))
        }
    }

    fun getGithubDetailUserFollower(username: String) : LiveData<Hasil<List<GithubUsers>>> = liveData {
        emit(Hasil.Loading)
        try {
            val data = UsersApiClient.api.getGithubDetailUserFollowers(username)
            if (data.isEmpty()){
                emit(Hasil.Empty)
            } else{
                emit(Hasil.Success(data))
            }
        } catch (e: Exception){
            Log.d("ANDIGITHUBFOLLOWING",e.message.toString())
            emit(Hasil.Error(e.message.toString()))
        }
    }

    fun getGithubDetailUserFollowing(username: String) : LiveData<Hasil<List<GithubUsers>>> = liveData {
        emit(Hasil.Loading)
        try {
            val data = UsersApiClient.api.getGithubDetailUserFollowing(username)
            if (data.isEmpty()){
                emit(Hasil.Empty)
            } else{
                emit(Hasil.Success(data))
            }
        } catch (e: Exception){
            emit(Hasil.Error(e.message.toString()))
        }
    }
}