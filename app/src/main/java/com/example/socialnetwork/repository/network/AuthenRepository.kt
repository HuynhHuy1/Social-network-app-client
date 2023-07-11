package com.example.socialnetwork.repository.network

import com.example.socialnetwork.api.ApiHelper
import com.example.socialnetwork.api.service.AuthenService
import com.example.socialnetwork.model.ReponseModel
import com.example.socialnetwork.model.UserModel
import retrofit2.Call
import retrofit2.http.Multipart

class AuthenRepository {
    val authenService = ApiHelper.getInstance().create(AuthenService::class.java)

    suspend fun login(userModel: UserModel): ReponseModel = authenService.login(userModel)

    suspend fun signup(userModel: UserModel): ReponseModel = authenService.signup(userModel)

    suspend fun comfirmPassword(email: String): ReponseModel = authenService.comfirmEmail(email)

    suspend fun comfirmNumberKey(numberKey: Map<String, Int>): ReponseModel =
        authenService.comfirmNumber(numberKey)

    suspend fun resetPassword(token: String, password: Map<String, String>): ReponseModel =
        authenService.resetPassword(
            token, password
        )
}