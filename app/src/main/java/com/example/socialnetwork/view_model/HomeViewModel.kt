package com.example.socialnetwork.view_model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.model.PostModel
import com.example.socialnetwork.model.UserModel
import com.example.socialnetwork.repository.network.HomeRepository
import com.example.socialnetwork.util.TokenManagerUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import convertBase64ToImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val homeRepository = HomeRepository()
    private var _listPost = MutableStateFlow<List<PostModel>>(emptyList())
    var listPost: StateFlow<List<PostModel>> = _listPost
    private var _userModel = mutableStateOf<UserModel>(UserModel())
    val userModel: State<UserModel> get() =  _userModel
    private var _comment = mutableStateOf("")
    val comment : State<String> get() = _comment

    suspend fun getPostFriend(context: Context) {
        return withContext(Dispatchers.IO) {
            try {
                val token = TokenManagerUtil(context).getToken()
                var listResponse  = homeRepository.postRepository(token!!)
                val gson = Gson()
                val jsonString = gson.toJson(listResponse.data)
                val type = object : TypeToken<List<PostModel>>() {}.type
                _listPost.value = gson.fromJson(jsonString, type)
            } catch (e: Exception) {
                Log.d("TAG", "getPostFriend: $e")
            }
        }
    }

    suspend fun getUser(context: Context) {
        return withContext(Dispatchers.IO){
            try {
                val token = TokenManagerUtil(context).getToken()
                var info = homeRepository.getUserProfile(token!!).data
                val gson = Gson()
                val jsonString = gson.toJson(info)
                val type = object : TypeToken<UserModel>() {}.type
                _userModel.value = gson.fromJson(jsonString, type)
                _userModel.value.imageBitmap = _userModel.value.avatar.let { convertBase64ToImageBitmap(_userModel.value.avatar!!) }
            } catch (e: Exception) {
                Log.d("TAG", "getPostFriend: $e")
            }
        }
    }

    fun handleLike(context: Context,path: Int){
        GlobalScope.launch(Dispatchers.IO) {
            val token = TokenManagerUtil(context).getToken()
            homeRepository.handleLike(token!!,path)
        }
    }

    fun updateComment(newValue : String){
        _comment.value = newValue
    }

    fun uploadComment(context: Context,path: Int){
        GlobalScope.launch(Dispatchers.IO){
            val token = TokenManagerUtil(context = context).getToken()
            val content = _comment.value
            val bodyContent = mapOf(Pair("content","$content"))
            homeRepository.handleComment(token!!,path,bodyContent)
            _comment.value = ""
        }
    }
}