package com.example.socialnetwork.repository.network

import com.example.socialnetwork.api.ApiHelper
import com.example.socialnetwork.api.service.HomeService
import com.example.socialnetwork.model.ReponseModel

class HomeRepository {
    val homeService = ApiHelper.getInstance().create(HomeService::class.java)

    suspend fun postRepository(token : String) : ReponseModel = homeService.getPostFollowing(token)

    suspend fun getUserProfile(token : String) : ReponseModel = homeService.getProfile(token)

    suspend fun handleLike(token : String,path : Int) = homeService.handleLike(token,path)

    suspend fun handleComment(token : String,path : Int,mapContent : Map<String,String>) = homeService.handleComment(token,path,mapContent)
}