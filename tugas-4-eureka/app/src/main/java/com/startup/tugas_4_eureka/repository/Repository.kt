package com.startup.tugas_4_eureka.repository

import com.startup.tugas_4_eureka.model.GithubDetailUser
import com.startup.tugas_4_eureka.model.GithubUsers
import com.startup.tugas_4_eureka.retrofit.UsersApiClient
import retrofit2.Response

class Repository {
    suspend fun getGithubUsers() : Response<List<GithubUsers>> {
        return UsersApiClient.api.getGithubUsers()
    }

    suspend fun getGithubDetailUser(username: String) : Response<GithubDetailUser> {
        return UsersApiClient.api.getGithubDetailUser(username)
    }

    suspend fun getGithubDetailUserFollower(username: String) : Response<List<GithubUsers>> {
        return  UsersApiClient.api.getGithubDetailUserFollowers(username)
    }

    suspend fun getGithubDetailUserFollowing(username: String) : Response<List<GithubUsers>> {
        return  UsersApiClient.api.getGithubDetailUserFollowing(username)
    }
}