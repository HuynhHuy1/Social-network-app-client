package com.example.socialnetwork.ui.screen.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.socialnetwork.R
import com.example.socialnetwork.model.CommentModel
import com.example.socialnetwork.model.PostModel
import com.example.socialnetwork.model.UserModel
import com.example.socialnetwork.ui.component.*
import com.example.socialnetwork.ui.theme.*
import com.example.socialnetwork.view_model.HomeViewModel
import com.example.socialnetwork.view_model.PostDetailViewModel
import com.squareup.moshi.Moshi
import convertBase64ToImageBitmap


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navHostController: NavHostController
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getPostFriend(context)
        viewModel.getUser(context)
    }
    val listPost by viewModel.listPost.collectAsState()
    val userModel by viewModel.userModel
    Log.d("TAG", "HomeScreen: ")
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            val image = painterResource(id = R.drawable.stories_all)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(width = 430.dp, height = 140.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        items(listPost) { item ->
            PostScreen(
                postModel = item,
                avatarUser = if (item.avatarBase64 == null) {
                    ImageBitmap.imageResource(id = R.drawable.avatar_null)
                } else {
                    convertBase64ToImageBitmap(item.avatarBase64)
                },
                state = item.stateLike,
                context = context,
                postID = item.postId,
                viewModel= viewModel,
                navHostController = navHostController,
                userID = item.userID
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun PostScreen(
    postModel: PostModel,
    avatarUser: ImageBitmap?,
    state: Boolean,
    context: Context,
    postID: Int,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navHostController: NavHostController,
    userID: Int = 0
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = Shapes.medium)
            .padding(20.dp),
    ) {
        HeaderPost(
            image = R.drawable.avatar_null,
            nameUser = postModel.userName!!,
            timeCreate = postModel.timeFormatString,
            avatarUser = avatarUser,
            navHostController = navHostController,
            userID = userID
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = postModel.content,
            style = subtitleStyle,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(20.dp))
        var image: ImageBitmap? = postModel.let {
            it.image?.let { it1 ->
                postModel.image?.let { it2 -> convertBase64ToImageBitmap(it2) }
            }
        }
        if (image != null) {
            Image(
                bitmap = image,
                contentDescription = null,
                modifier = Modifier
                    .clip(Shapes.medium)
                    .fillMaxWidth()
                    .border(width = 0.1.dp, color = loginLine),
                contentScale = ContentScale.FillBounds,
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        FotterPost(
            numberLike = postModel.likeCount,
            numberComment = postModel.commentCount,
            state = state,
            context = context,
            viewModel = viewModel,
            postModel = postModel,
            navHostController = navHostController
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextFieldSendComment(avatarUser = avatarUser, viewModel, context, postID)
    }
}

@Composable
fun HeaderPost(
    image: Int,
    nameUser: String,
    timeCreate: String,
    modifier: Modifier = Modifier,
    avatarUser: ImageBitmap?,
    navHostController: NavHostController? = null,
    userID: Int = 0
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            bitmap = (avatarUser ?: ImageBitmap.imageResource(id = R.drawable.avatar_null)) ,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp, 50.dp)
                .clip(CircleShape)
                .clickable {
                    navHostController!!.currentBackStackEntry?.arguments?.putInt(
                        "id", userID
                    )
                    Log.d("TAG", "HeaderPost: $userID")
                    navHostController.navigate("profile")
                }
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(
                text = nameUser,
                style = subtitleStyle,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = timeCreate,
                style = textContentShadowStyle,
            )
        }
    }
}

@Composable
fun UserSearch(image: ImageBitmap, nameUser: String, modifier: Modifier = Modifier, navHostController: NavHostController? = null,id : Int = 0) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            bitmap = image,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp, 60.dp)
                .clip(CircleShape)
                .clickable {
                    navHostController!!.currentBackStackEntry?.arguments?.putInt(
                        "id", id
                    )
                    navHostController!!.navigate("profile")
                }
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(
                text = nameUser,
                style = searchStyle,
            )
        }
    }
}

@Composable
fun FotterPost(
    numberLike: Int,
    numberComment: Int,
    state: Boolean,
    viewModel: HomeViewModel,
    context: Context,
    postModel: PostModel,
    navHostController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        var likeState by remember {
            mutableStateOf(state)
        }
        var likeNumber by remember {
            mutableStateOf(numberLike)
        }
        val heartIconFill = painterResource(id = R.drawable.heart_icon)
        val heartIcon = painterResource(id = R.drawable.heart)
        if (likeState) {
            Icon(
                painter = heartIconFill, contentDescription = null,
                Modifier
                    .size(24.dp)
                    .clickable {
                        viewModel.handleLike(context = context, path = postModel.postId)
                        likeState = !likeState
                        likeNumber = likeNumber - 1
                    }
            )
        } else {
            Icon(
                painter = heartIcon, contentDescription = null,
                Modifier
                    .size(24.dp)
                    .clickable {
                        viewModel.handleLike(context = context, path = postModel.postId)
                        likeState = !likeState
                        likeNumber = likeNumber + 1
                    }
            )
        }

        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = if (likeState) {
                "${likeNumber} "
            } else {
                "${likeNumber}"
            },
            style = MediumStyle
        )
        Spacer(modifier = Modifier.width(20.dp))

        val commentIcon = painterResource(id = R.drawable.comment_icon)
        Icon(
            painter = commentIcon,
            contentDescription = null,
            Modifier
                .size(24.dp)
                .clickable {
                    navHostController.currentBackStackEntry?.arguments?.putParcelable(
                        "postModel",
                        postModel
                    )
                    Log.d("TAG", "FotterPost: ${postModel.postId}")
                    navHostController.navigate("post_detail")
                }
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "$numberComment", style = MediumStyle
        )
    }
}

@Composable
fun TextFieldSendComment(
    avatarUser: ImageBitmap?,
    viewModel: HomeViewModel,
    context: Context,
    path: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Spacer(modifier = Modifier.width(10.dp))
        TextFieldComment(
            textLabel = "Write a comment",
            viewModel = viewModel,
            context = context,
            path = path,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TextFieldSendCommentInBox(avatarUser: Int, viewModel: HomeViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f)
            .padding(top = 30.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val image = painterResource(id = avatarUser)
        Image(
            painter = image, contentDescription = null, Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        TextFieldComment(textLabel = "Write a comment", viewModel)
    }
}


@Composable
fun TextFieldComment(
    textLabel: String,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    context: Context? = null,
    path: Int = 0,
    modifier: Modifier = Modifier
) {
    TextField(value = viewModel.comment.value,
        onValueChange = {
            viewModel.updateComment(it)
        },
        modifier = modifier.fillMaxWidth(),
        textStyle = nomarStyle,
        placeholder = { Text(text = textLabel) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundPost,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        shape = Shapes.medium,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier.clickable {
                    viewModel.uploadComment(context!!, path)
                },

                )
        })
}

@Composable
fun ListComment(
    navHostController: NavHostController,
    listComment: List<CommentModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(listComment) {
            ContentComment(navHostController, it)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ContentComment(navHostController: NavHostController, commentModel: CommentModel) {
    var avatar: ImageBitmap? = commentModel.let {
        it.avatar?.let { it1 ->
            commentModel.avatar?.let { it2 -> convertBase64ToImageBitmap(it2) }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        if (avatar != null) {
            IconAvatar(avatar, "profile", navHostController)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = commentModel.userName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = nomarStyle
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = commentModel.content, style = textContentShadowStyle
            )
        }
    }
}
