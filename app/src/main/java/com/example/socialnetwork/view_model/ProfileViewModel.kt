package com.example.socialnetwork.view_model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.api.ApiHelper
import com.example.socialnetwork.api.service.ProfileService
import com.example.socialnetwork.model.PostModel
import com.example.socialnetwork.model.ReponseModel
import com.example.socialnetwork.model.UserModel
import com.example.socialnetwork.util.TokenManagerUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class ProfileViewModel : ViewModel() {
    val profileService = ApiHelper.getInstance().create(ProfileService::class.java)
    private var _userDto = mutableStateOf(UserModel())
    var userDto: State<UserModel> = _userDto
    private var _listImage = MutableStateFlow<List<PostModel>>(arrayListOf())
    var listImage: StateFlow<List<PostModel>> = _listImage
    private var _isFollowing = mutableStateOf(false)
    var isFolowing: State<Boolean> = _isFollowing
    suspend fun getUserProfile(userID: Int, context: Context) {
        withContext(Dispatchers.IO) {
            val token = TokenManagerUtil(context).getToken()
            val call: Call<ReponseModel> = profileService.getProfile(token!!, userID)
            val response: Response<ReponseModel> = call.execute()
            if (response.isSuccessful) {
                var data = response.body()!!.data
                val gson = Gson()
                val jsonString = gson.toJson(data)
                val type = object : TypeToken<UserModel>() {}.type
                _userDto.value = gson.fromJson(jsonString, type)
                Log.d("TAG", "getUserProfile: ${_userDto.value.toString()} ")
            }
        }
    }

    suspend fun getImageProfile(userID: Int, context: Context) {
        withContext(Dispatchers.IO) {
            val token = TokenManagerUtil(context).getToken()
            val call: Call<ReponseModel> = profileService.getImageProfile(token!!, userID)
            val response: Response<ReponseModel> = call.execute()
            if (response.isSuccessful) {
                var data = response.body()!!.data
                val gson = Gson()
                val jsonString = gson.toJson(data)
                val type = object : TypeToken<List<PostModel>>() {}.type
                _listImage.value = gson.fromJson(jsonString, type)
            }
        }
    }

    suspend fun isFollowing(userID: Int, context: Context) {
        withContext(Dispatchers.IO) {
            val token = TokenManagerUtil(context).getToken()
            val call: Call<ReponseModel> = profileService.getFollowing(token!!)
            val response: Response<ReponseModel> = call.execute()
            if (response.isSuccessful) {
                var data = response.body()!!.data
                val gson = Gson()
                val jsonString = gson.toJson(data)
                val type = object : TypeToken<List<UserModel>>() {}.type
                var listUser: List<UserModel> = gson.fromJson(jsonString, type)
                Log.d("TAG", "isFollowing: $listUser")
                listUser.forEach {
                    if (it.id == userID) {
                        _isFollowing.value = true
                    }
                }
            }
        }
    }

    fun handleOnClickFollow(context: Context, userID: Int) {
        GlobalScope.launch(Dispatchers.IO){
            val token = TokenManagerUtil(context).getToken()
            val call: Call<ReponseModel> = profileService.postFollowing(token!!,userID)
            val response: Response<ReponseModel> = call.execute()
            if (response.isSuccessful) {
                _isFollowing.value = !_isFollowing.value
            }
        }
    }
    fun handleLogout(context: Context){
        var tokenManagerUtil = TokenManagerUtil(context = context)
        tokenManagerUtil.clearToken()
        tokenManagerUtil.clearID()
        tokenManagerUtil.clearTokenResetPassword()
    }

}
