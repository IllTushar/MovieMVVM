package com.example.movieapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val BASE_URL = "https://Movies-Verse.proxy-production.allthingsdev.co/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }
}