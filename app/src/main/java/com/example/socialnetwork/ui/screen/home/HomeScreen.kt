package com.example.socialnetwork.ui.screen.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.model.CommentModel
import com.example.socialnetwork.model.PostModel
import com.example.socialnetwork.ui.component.*
import com.example.socialnetwork.ui.theme.*

val postModel = PostModel(
    avatarUserPost = R.drawable.avatar_user_post,
    avatarUser = R.drawable.avatar_user,
    name = "Floyd Miles",
    timeCreate = "10 hours ago",
    image = R.drawable.image_post,
    content = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using",
    likeNumber = 2000,
    commentNumber = 80,
)


@Composable
fun HomeScreen() {
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
                modifier = Modifier.size(width = 430.dp, height = 140.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(5) {
            PostScreen(postModel = postModel, avatarUser = R.drawable.avatar_user)
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun PostScreen(postModel: PostModel, @DrawableRes avatarUser: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = Shapes.medium)
            .padding(20.dp),
    ) {
        HeaderPost(image = postModel.avatarUserPost, nameUser = postModel.name)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = postModel.content,
            style = contentStyle,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = postModel.timeCreate,
            style = textContentShadowStyle,
        )
        Spacer(modifier = Modifier.height(20.dp))
        val image = painterResource(id = postModel.image)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .clip(Shapes.medium)
                .height(height = 300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(15.dp))
        FotterPost(numberLike = postModel.likeNumber, numberComment = postModel.commentNumber)
        Spacer(modifier = Modifier.height(15.dp))
        TextFieldSendComment(avatarUser = postModel.avatarUserPost)
    }
}

@Composable
fun HeaderPost(image: Int, nameUser: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val image = painterResource(id = image)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp, 50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = nameUser,
            style = nomarStyle,
        )
    }
}

@Composable
fun FotterPost(numberLike: Int, numberComment: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        val heartIcon = painterResource(id = R.drawable.hreart_icon)
        Icon(
            painter = heartIcon, contentDescription = null, Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "$numberLike", style = MediumStyle
        )
        Spacer(modifier = Modifier.width(20.dp))

        val commentIcon = painterResource(id = R.drawable.comment_icon)
        Icon(
            painter = commentIcon, contentDescription = null, Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "$numberComment", style = MediumStyle
        )
    }
}

@Composable
fun TextFieldSendComment(avatarUser: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        val image = painterResource(id = avatarUser)
        Image(
            painter = image, contentDescription = null, Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        TextFieldComment(textLabel = "Write a comment")
    }
}

@Composable
fun TextFieldSendCommentInBox(avatarUser: Int) {
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
        TextFieldComment(textLabel = "Write a comment")
    }
}


@Composable
fun TextFieldComment(textLabel: String) {
    var value by remember {
        mutableStateOf("")
    }
    TextField(value = value,
        onValueChange = {
            value = it
        },
        modifier = Modifier.fillMaxWidth(),
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
        trailingIcon = { Icon(imageVector = Icons.Default.Send, contentDescription = null) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogShowComment(navHostController: NavHostController) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        androidx.compose.material3.Scaffold(
            Modifier
                .fillMaxWidth(0.9f)
                .heightIn(min = 300.dp, max = 400.dp)
                .padding(vertical = 40.dp, horizontal = 15.dp),
            content = {
                ListComment(navHostController)
            },
            bottomBar = {
                TextFieldSendCommentInBox(avatarUser = R.drawable.avatar_user)
            })
    }
}

@Composable
fun ListComment(navHostController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(3) {
            ContentComment(navHostController)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ContentComment(navHostController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        IconCustom(icon = R.drawable.avatar_user,"profile", navHostController )
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Hello Huy",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = nomarStyle
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "24h", style = textContentShadowStyle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    HomeScreen()
}