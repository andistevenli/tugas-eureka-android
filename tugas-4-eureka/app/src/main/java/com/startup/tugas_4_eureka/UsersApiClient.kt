package com.startup.tugas_4_eureka

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UsersApiClient {
    val base_url = "https://api.github.com/"
    val apiService: UsersApiService
        get() {
            val interceptor= HttpLoggingInterceptor()
            interceptor.level= HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder().client(client).addConverterFactory(
                GsonConverterFactory.create()).baseUrl(base_url).build()
            return retrofit.create(UsersApiService::class.java)
        }
}