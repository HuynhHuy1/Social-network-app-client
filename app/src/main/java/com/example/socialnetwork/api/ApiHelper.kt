package com.example.socialnetwork.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    val BASE_URL = "http://192.168.1.121:8090/"
    fun getInstance () : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}