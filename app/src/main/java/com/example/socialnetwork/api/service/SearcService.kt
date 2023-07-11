package com.example.socialnetwork.api.service

import com.example.socialnetwork.model.ReponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SearcService {

    @GET("api/users/{name}")

    fun getUserByName(
        @Header("token") token : String,
        @Path("name") name : String,
    ) : Call<ReponseModel>
}