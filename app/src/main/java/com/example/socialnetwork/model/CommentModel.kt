package com.example.socialnetwork.model

import androidx.annotation.DrawableRes
import com.example.socialnetwork.R

data class CommentModel(
    @DrawableRes val avatarUser: Int = R.drawable.avatar_null,
    val content: String = "",
    val timeCreate : String,
)