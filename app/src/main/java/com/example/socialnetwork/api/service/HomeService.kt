package com.example.socialnetwork.api.service

import com.example.socialnetwork.model.ReponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeService {
    @GET("api/posts/following")
    suspend fun getPostFollowing(@Header("token") token : String) : ReponseModel

    @GET("api/users/profile")
    suspend fun getProfile(@Header("token") token: String) : ReponseModel

    @POST("api/posts/{post}/likes/handle-like")
    suspend fun handleLike(@Header("token") token : String,@Path("post") postId : Int)

    @POST("api/posts/{post-id}/comments")
    suspend fun handleComment(@Header("token") token : String,@Path("post-id") postId : Int,@Body mapContent : Map<String,String>)
}