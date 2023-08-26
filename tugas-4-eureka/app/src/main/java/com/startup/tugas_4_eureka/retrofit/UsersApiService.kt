package com.startup.tugas_4_eureka.retrofit

import com.startup.tugas_4_eureka.model.GithubDetailUser
import com.startup.tugas_4_eureka.model.GithubUsers
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApiService {
    @GET("users")
    suspend fun getGithubUsers() :  Response<List<GithubUsers>>

    @GET("users/{username}")
    suspend fun getGithubDetailUser(
        @Path ("username") username: String
    ) : Response<GithubDetailUser>

    @GET("users/{username}/followers")
    suspend fun getGithubDetailUserFollowers(
        @Path ("username") username: String
    ) : Response<List<GithubUsers>>

    @GET("users/{username}/following")
    suspend fun getGithubDetailUserFollowing(
        @Path ("username") username: String
    ) : Response<List<GithubUsers>>
}