package com.example.socialnetwork.view_model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.api.ApiHelper
import com.example.socialnetwork.api.service.SearcService
import com.example.socialnetwork.model.CommentModel
import com.example.socialnetwork.model.PostModel
import com.example.socialnetwork.model.ReponseModel
import com.example.socialnetwork.model.UserModel
import com.example.socialnetwork.ui.screen.home.SearchScreen
import com.example.socialnetwork.util.TokenManagerUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.create
import java.util.Stack

class SearchViewModel : ViewModel() {
    var searcService = ApiHelper.getInstance().create(SearcService::class.java)
    private var _listUserSearch = MutableStateFlow<List<UserModel>>(emptyList())
    var listUserSearch: StateFlow<List<UserModel>> = _listUserSearch
    private var _contentSearch = mutableStateOf("")
    val contentSearch : State<String> get() = _contentSearch
    fun updateContentSearch(content : String){
        _contentSearch.value = content
    }
    suspend fun searchUser(context : Context){
        withContext(Dispatchers.IO){
            val token = TokenManagerUtil(context).getToken()
            val contentSearch = _contentSearch.value
            val call: Call<ReponseModel> = searcService.getUserByName(token!!,contentSearch)
            val response: Response<ReponseModel> = call.execute()
            if (response.isSuccessful) {
                var data = response.body()!!.data
                val gson = Gson()
                val jsonString = gson.toJson(data)
                val type = object : TypeToken<List<UserModel>>() {}.type
                _listUserSearch.value = gson.fromJson(jsonString, type)
            } else {
                Log.d("TAG", "searchUser: Cuộc gọi API không thành công")
            }
        }
    }
}