package com.example.socialnetwork.api.service

import com.example.socialnetwork.model.ReponseModel
import com.example.socialnetwork.model.UserModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AuthenService {
    @POST("auth/login")
    suspend fun login(@Body userModel: UserModel): ReponseModel

    @POST("auth/signUp")
    suspend fun signup(@Body userModel: UserModel): ReponseModel

    @POST("auth/forgot-password")
    suspend fun comfirmEmail(@Body email: String): ReponseModel

    @POST("auth/check-key-number")
    suspend fun comfirmNumber(@Body mapKeynumber: Map<String, Int>): ReponseModel

    @POST("auth/reset-password")
    suspend fun resetPassword(
        @Header("keyNumber") token : String , @Body mapPassword: Map<String, String>
    ): ReponseModel

    @GET("api/users/profile/userID")
    fun getIdByToken(
        @Header("token" ) token : String) : ReponseModel

    @Multipart
    @PUT("api/information")
    suspend fun updateInfo(
        @Header("token") token: String,
        @Part("userName") name : RequestBody?,
        @Part("address") address : RequestBody?,
        @Part("phone") phone : RequestBody?,
        @Part avatar: MultipartBody.Part?,
    )
}