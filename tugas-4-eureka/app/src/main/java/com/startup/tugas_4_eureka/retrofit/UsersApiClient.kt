package com.startup.tugas_4_eureka.retrofit

import com.startup.tugas_4_eureka.retrofit.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object UsersApiClient {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: UsersApiService by lazy {
        retrofit.create(UsersApiService::class.java)
    }

//    val apiService: UsersApiService
//        get() {
//            val interceptor= HttpLoggingInterceptor()
//            interceptor.level= HttpLoggingInterceptor.Level.BODY
//            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//            val retrofit = Retrofit.Builder().client(client).addConverterFactory(
//                GsonConverterFactory.create()).baseUrl(base_url).build()
//            return retrofit.create(UsersApiService::class.java)
//        }
}