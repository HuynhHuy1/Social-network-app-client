package com.example.socialnetwork.api.service

import com.example.socialnetwork.model.ReponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileService {
    @GET("api/users/profile/{id}")
    fun getProfile(
        @Header("token") token : String,
        @Path("id") userId : Int
    ) : Call<ReponseModel>

    @GET("api/users/profile/image/{id}")
    fun getImageProfile(
        @Header("token" ) token : String,
        @Path("id") userId: Int
    ) : Call<ReponseModel>

    @GET("api/friendships/following")
    fun getFollowing(
        @Header("token") token : String
    ) : Call<ReponseModel>

    @POST("api/friendships/following/{user_id}")
    fun postFollowing(
        @Header("token") token : String,
        @Path("user_id") userId: Int
    ) : Call<ReponseModel>
}