package com.example.socialnetwork.util

import android.content.Context

class TokenManagerUtil(context : Context) {
    private val sharedPreferences = context.getSharedPreferences("TokenPrefs", Context.MODE_PRIVATE)
    private val tokenKey = "token"
    private val tokenResetPasswordKey = "token_reset_password"

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(tokenKey, token).apply()
    }

    fun saveTokenResetPassword(token: String) {
        sharedPreferences.edit().putString(tokenResetPasswordKey, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(tokenKey, null)
    }

    fun getTokenResetPassword(): String? {
        return sharedPreferences.getString(tokenResetPasswordKey, null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove(tokenKey).apply()
    }

    fun clearTokenResetPassword() {
        sharedPreferences.edit().remove(tokenResetPasswordKey).apply()
    }

    fun saveID(id: Int) {
        sharedPreferences.edit().putInt("id", id).apply()
    }

    fun getID(): Int {
        return sharedPreferences.getInt("id", 0)
    }

    fun clearID() {
        sharedPreferences.edit().remove("id").apply()
    }
}