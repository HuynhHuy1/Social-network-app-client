package com.example.socialnetwork.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.api.ApiHelper
import com.example.socialnetwork.api.service.PostService
import com.example.socialnetwork.model.CommentModel
import com.example.socialnetwork.model.PostModel
import com.example.socialnetwork.ui.screen.home.HomeScreen
import com.example.socialnetwork.util.TokenManagerUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class PostDetailViewModel : ViewModel() {
    private var _listComment = MutableStateFlow<List<CommentModel>>(emptyList())
    val listComment: StateFlow<List<CommentModel>> = _listComment
    var postService = ApiHelper.getInstance().create(PostService::class.java)

    suspend fun getComment(postId: Int, context: Context) {
        return withContext(Dispatchers.IO){
            val token = TokenManagerUtil(context).getToken()
            val responseModel = postService.getComment(token = token!!, postID = postId)
            if (responseModel.status.equals("Success")) {
                val gson = Gson()
                val jsonString = gson.toJson(responseModel.data)
                val type = object : TypeToken<List<CommentModel>>() {}.type
                _listComment.value = gson.fromJson(jsonString, type)
                Log.d("TAG", "getComment: ${_listComment.value.size} ")
            }
            Log.d("TAG", "getComment: 123")
        }
    }
}