package com.example.socialnetwork.api.service

import com.example.socialnetwork.model.CommentModel
import com.example.socialnetwork.model.ReponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.http.*
import java.io.File

interface PostService {
    @Multipart

    @POST("api/posts")
    suspend fun uploadPost(
        @Header("token") token: String,
        @Part("Content") content : RequestBody?,
        @Part image: MultipartBody.Part?,
    )

    @GET("api/posts/{post_id}/comments")
    suspend fun getComment(
        @Header("token") token : String,
        @Path("post_id") postID : Int
    ) : ReponseModel
}