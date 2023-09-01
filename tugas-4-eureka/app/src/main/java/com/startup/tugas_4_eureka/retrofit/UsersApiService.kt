package com.startup.tugas_4_eureka.retrofit

import com.startup.tugas_4_eureka.model.GithubDetailUser
import com.startup.tugas_4_eureka.model.GithubUsers
import com.startup.tugas_4_eureka.repository.Hasil
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UsersApiService {
    @GET("users")
    suspend fun getGithubUsers(
    ) :  List<GithubUsers>

    @GET("users/{username}")
    suspend fun getGithubDetailUser(
        @Path ("username") username: String
    ) : GithubDetailUser

    @GET("users/{username}/followers")
    suspend fun getGithubDetailUserFollowers(
        @Path ("username") username: String
    ) : List<GithubUsers>

    @GET("users/{username}/following")
    suspend fun getGithubDetailUserFollowing(
        @Path ("username") username: String
    ) : List<GithubUsers>
}