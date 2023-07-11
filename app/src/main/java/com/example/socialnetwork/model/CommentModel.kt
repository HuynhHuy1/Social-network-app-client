package com.example.socialnetwork.model

import androidx.annotation.DrawableRes
import com.example.socialnetwork.R

data class CommentModel(
    val id : Int = 0,
    val postId : Int = 0,
    val userId : Int = 0,
    val userName : String = "",
    val content: String = "",
    val avatar : String? = null)