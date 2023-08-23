package com.startup.tugas_4_eureka

import retrofit2.Call
import retrofit2.http.GET

interface UsersApiService {
    @GET("users")
    fun getUsers(): Call<ArrayList<GithubUsers>>
}