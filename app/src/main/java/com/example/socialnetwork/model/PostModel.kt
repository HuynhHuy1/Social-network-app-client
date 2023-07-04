package com.example.socialnetwork.model

import androidx.annotation.DrawableRes
import com.example.socialnetwork.R

data class PostModel(
    @DrawableRes val avatarUserPost: Int = R.drawable.avatar_null,
    @DrawableRes val avatarUser: Int = R.drawable.avatar_null,
    @DrawableRes val image: Int = 0,
    val name: String,
    val content: String = "",
    val likeNumber : Int = 0,
    val commentNumber : Int = 0,
    val timeCreate : String,
)