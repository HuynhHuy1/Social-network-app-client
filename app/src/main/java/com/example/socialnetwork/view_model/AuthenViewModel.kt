package com.example.socialnetwork.view_model

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.api.ApiHelper
import com.example.socialnetwork.api.service.AuthenService
import com.example.socialnetwork.model.ReponseModel
import com.example.socialnetwork.model.UserModel
import com.example.socialnetwork.repository.network.AuthenRepository
import com.example.socialnetwork.util.TokenManagerUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import convertUriToImageFile
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

class AuthenViewModel : ViewModel() {
    val authenRepository = AuthenRepository()
    val authenService = ApiHelper.getInstance().create(AuthenService::class.java)
    private var _password = mutableStateOf("")
    val password: State<String>
        get() = _password
    private var _email = mutableStateOf("")
    val email: State<String>
        get() = _email
    private var _userName = mutableStateOf("")
    val userName: State<String>
        get() = _userName
    private var _userAddress = mutableStateOf("")
    val userAddress: State<String>
        get() = _userAddress
    private var _userPhone = mutableStateOf("")
    val userPhone: State<String>
        get() = _userPhone
    private var _rePassword = mutableStateOf("")
    val rePassword: State<String>
        get() = _rePassword
    private var _otp = mutableStateOf("")
    val otp: State<String>
        get() = _otp

    var _uri = mutableStateOf<Uri?>(null)
    val uri: State<Uri?> get() = _uri

    fun updatePassword(valuePassword: String) {
        _password.value = valuePassword
    }

    fun updateEmail(valueEmail: String) {
        _email.value = valueEmail
    }

    fun updateUri(uri: Uri?) {
        _uri.value = uri
    }

    fun updateUserName(valueUserName: String) {
        _userName.value = valueUserName
    }

    fun updateUserAddress(valueUserName: String) {
        _userAddress.value = valueUserName
    }

    fun updateUserPhone(valueUserName: String) {
        _userPhone.value = valueUserName
    }

    fun updateRePassword(valueRePassword: String) {
        _rePassword.value = valueRePassword
    }

    fun updateOTP(valueRePassword: String) {
        _otp.value = valueRePassword
    }

    suspend fun login(context: Context): ReponseModel {
        val userModel = UserModel(email = this.email.value, password = this.password.value)
        return withContext(Dispatchers.IO) {
            var response = authenRepository.login(userModel)
            if (response.status.equals("Success")) {
                val gson = Gson()
                val jsonString = gson.toJson(response.data)
                val type = object : TypeToken<Map<String, Any>>() {}.type
                val mapLogin: Map<String, Any> = gson.fromJson(jsonString, type)
                val token = mapLogin.get("token")
                val idDouble = mapLogin.get("id")
                var id: Int = java.lang.Double.valueOf(idDouble as Double).toInt()
                TokenManagerUtil(context).saveID(id as Int)
                TokenManagerUtil(context).saveToken(token as String)
            }
            return@withContext response
        }
    }

    suspend fun signup(context: Context): ReponseModel {
        return withContext(Dispatchers.IO) {
            if (password.value != rePassword.value) {
                return@withContext ReponseModel("Password Error")
            } else {
                var userModel =
                    UserModel(name = userName.value, email = email.value, password = password.value)
                var response = authenRepository.signup(userModel)
                if (response.status.equals("Success")) {
                    val gson = Gson()
                    val jsonString = gson.toJson(response.data)
                    val type = object : TypeToken<Map<String, Any>>() {}.type
                    val mapLogin: Map<String, Any> = gson.fromJson(jsonString, type)
                    val token = mapLogin.get("token")
                    val idDouble = mapLogin.get("id")
                    var id: Int = java.lang.Double.valueOf(idDouble as Double).toInt()
                    TokenManagerUtil(context).saveID(id as Int)
                    TokenManagerUtil(context).saveToken(token as String)
                }
                return@withContext response
            }
        }
    }

    suspend fun forgotPassword(): Boolean {
        return withContext(Dispatchers.IO) {
            val reponseModel = authenRepository.comfirmPassword(email.value)
            return@withContext reponseModel.status.equals("Success")
        }
    }

    suspend fun checkNumberKey(number: Int, context: Context): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                var mapNumber = mapOf(Pair("keyNumber", number))
                val reponseModel = authenRepository.comfirmNumberKey(mapNumber)
                TokenManagerUtil(context).saveTokenResetPassword(reponseModel.data.toString())
                return@withContext reponseModel.status.equals("Success")
            } catch (e: Exception) {
                Log.d("TAG", "checkNumberKey: ${e}")
                return@withContext false
            }
        }
    }

    suspend fun resetPassword(context: Context): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                if (password.value != rePassword.value) {
                    return@withContext false
                }
                var mapPassword = mapOf(Pair("password", password.value))
                val tokenResetPassword = TokenManagerUtil(context).getTokenResetPassword()
                val reponseModel = authenRepository.resetPassword(tokenResetPassword!!, mapPassword)
                TokenManagerUtil(context).saveToken(reponseModel.data.toString())
                return@withContext reponseModel.status.equals("Success")
            } catch (e: Exception) {
                Log.d("TAG", "resetPassword: $e")
                return@withContext false
            }

        }
    }

    fun handleUpdate(context: Context,userModel: UserModel) {
        GlobalScope.launch(Dispatchers.IO) {
            val authenService = ApiHelper.getInstance().create(AuthenService::class.java)
            val token = TokenManagerUtil(context).getToken()
            val phone = if (_userPhone.value == "") userModel.phone else _userPhone.value
            val address = if (_userAddress.value == "") userModel.address else _userAddress.value
            val name = if (_userName.value == "") userModel.name else _userName.value
            val imageByte = convertUriToImageFile(_uri.value, context)
            val phoneRequestBody = phone?.toRequestBody("text/plain".toMediaTypeOrNull())
            val nameRequestBody = name?.toRequestBody("text/plain".toMediaTypeOrNull())
            val addressRequestBody = address?.toRequestBody("text/plain".toMediaTypeOrNull())
            val imageRequestBody = imageByte?.toRequestBody("image/jpeg".toMediaTypeOrNull())
            val multipartImage = imageRequestBody?.let {
                MultipartBody.Part.createFormData(
                    "avatar",
                    "avatar" + UUID.randomUUID().toString(),
                    it
                )
            }
            authenService.updateInfo(
                token = token!!,
                phone = phoneRequestBody,
                name = nameRequestBody,
                address = addressRequestBody,
                avatar = multipartImage
            )
            _uri.value = null
            _userName.value = ""
            _userPhone.value = ""
            _userAddress.value = ""
        }

        fun isValidEmail(email: String): Boolean {
            val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+$")
            return email.matches(emailRegex)
        }

        fun validForm(text: String): Boolean {
            if (text == "") return false
            return true
        }
    }
}

