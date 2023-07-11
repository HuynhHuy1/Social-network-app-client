package com.example.socialnetwork.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.socialnetwork.model.PostModel
import com.example.socialnetwork.view_model.HomeViewModel
import com.example.socialnetwork.view_model.PostDetailViewModel
import convertBase64ToImageBitmap
import java.sql.Timestamp


@Composable
fun DetailPostScreen(postModel: PostModel, navHostController: NavHostController) {
    val context = LocalContext.current
    val postDetailViewModel = remember { PostDetailViewModel() }

    LaunchedEffect(Unit) {
        postDetailViewModel.getComment(postModel.postId, context)
        Log.d("TAG", "DetailPostScreen: 111")
    }

    val listComment by postDetailViewModel.listComment.collectAsState()
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("TAG", "DetailPostScreen: ${listComment.size}")
        PostScreen(
            postModel = postModel,
            navHostController = navHostController,
            state = postModel.stateLike,
            context = context,
            postID = postModel.postId,
            avatarUser = postModel.avatarBase64?.let { convertBase64ToImageBitmap(it) }
        )
        ListComment(
            navHostController,
            modifier = Modifier.padding(horizontal = 10.dp),
            listComment = listComment
        )

    }
}
