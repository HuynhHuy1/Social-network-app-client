package com.example.socialnetwork.view_model

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.api.ApiHelper
import com.example.socialnetwork.api.service.PostService
import com.example.socialnetwork.util.TokenManagerUtil
import convertUriToImageFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

class PostViewModel : ViewModel() {
    val postService = ApiHelper.getInstance().create(PostService::class.java)
    var _uri = mutableStateOf<Uri?>(null)
    val uri : State<Uri?>   get() = _uri

    var _content = mutableStateOf("")
    val content : State<String> get() = _content

    fun updateUri(uri : Uri?){
        _uri.value = uri
    }
    fun updateContent(content : String){
        _content.value = content
    }

    fun handleClickImage(context : Context){
        GlobalScope.launch(Dispatchers.IO) {
            val token = TokenManagerUtil(context).getToken()
            val content = _content.value
            val imageByte = convertUriToImageFile(_uri.value, context)
            val contentRequestBody = content?.toRequestBody("text/plain".toMediaTypeOrNull())
            val imageRequestBody = imageByte?.toRequestBody("image/jpeg".toMediaTypeOrNull())
            val multipartImage = imageRequestBody?.let { MultipartBody.Part.createFormData("Images", "image" + UUID.randomUUID().toString(), it) }
            postService.uploadPost(token = token!!,contentRequestBody,multipartImage)
            _uri.value = null
            _content.value =""
        }
    }
}