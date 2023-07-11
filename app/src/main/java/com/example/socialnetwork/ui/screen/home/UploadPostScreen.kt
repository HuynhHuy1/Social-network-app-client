package com.example.socialnetwork.ui.screen.home

import android.annotation.SuppressLint
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.component.Navbar
import com.example.socialnetwork.ui.theme.*
import com.example.socialnetwork.view_model.PostDetailViewModel
import com.example.socialnetwork.view_model.PostViewModel

@Composable
fun UploadPostScreen(viewModel : PostViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var context = LocalContext.current
        Box(
        ) {
            Text(
                text = " Create Post",
                style = titleStyle,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
        textFieldPost(textLabel = "What's on your mind ?",viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        val imagePicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                viewModel.updateUri(it)
            })
        uploadImage(uri = viewModel.uri.value, this)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconCamera = painterResource(id = R.drawable.camera_solod)
            Icon(
                painter = iconCamera,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            val iconImage = painterResource(id = R.drawable.image_solid)
            Icon(
                painter = iconImage,
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        imagePicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
            )
            Spacer(modifier = Modifier.width(200.dp))
            buttonUpload(viewModel,context, navHostController = navHostController)
        }
    }
}

@Composable
fun textFieldPost(textLabel: String,viewModel: PostViewModel) {
    TextField(
        value = viewModel.content.value,
        onValueChange = {
            viewModel.updateContent(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp)
            .height(300.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        shape = Shapes.medium,
        textStyle = contentStyle,
        placeholder = { Text(text = textLabel) },
    )
}

@Composable
fun buttonUpload(viewModel: PostViewModel,context : Context,navHostController: NavHostController) {
    Button(
        colors = ButtonDefaults.buttonColors(startLoginBackground),
        onClick = {
            viewModel.handleClickImage(context)
            navHostController.navigate("home")},
    ) {
        Text(text = "Post", style = buttonStyleWhite)

    }
}

@Composable
fun uploadImage(uri: Uri?, columnScope: ColumnScope) {
    if (uri != null) {
        columnScope.run {
            Box {
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(Shapes.medium),
                    contentScale = ContentScale.Fit,
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 20.dp, end = 20.dp)
                        .align(Alignment.TopEnd),
                )
            }
        }
    }
}