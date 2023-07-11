package com.example.socialnetwork.ui.screen.home

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.socialnetwork.R
import com.example.socialnetwork.model.UserModel
import com.example.socialnetwork.ui.component.IconAvatar
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.component.textFieldLoginComponent
import com.example.socialnetwork.ui.component.textFieldPassword
import com.example.socialnetwork.ui.screen.signupScreen
import com.example.socialnetwork.ui.theme.Shapes
import com.example.socialnetwork.ui.theme.loginBackgroundGradient
import com.example.socialnetwork.view_model.AuthenViewModel
import com.example.socialnetwork.view_model.PostViewModel
import convertBase64ToImageBitmap

@Composable
fun updateInfoScreen(
    viewModel: AuthenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    userModel: UserModel,
    navHostController: NavHostController
) {
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            viewModel.updateUri(it)
        })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 40.dp, end = 25.dp, start = 25.dp)
    ) {
        val context = LocalContext.current
        if (viewModel.uri.value == null) {
            Image(
                bitmap = if (userModel.avatar?.let { convertBase64ToImageBitmap(it) } != null) {
                    convertBase64ToImageBitmap(userModel.avatar)!!
                } else {
                    ImageBitmap.imageResource(id = R.drawable.avatar_null)
                },
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .clickable {
                        imagePicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
            )
        } else {
            AsyncImage(
                model = viewModel.uri.value,
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds,
            )
        }
        formLoginScreen(viewModel = viewModel, stateError = false,userModel = userModel)
        Spacer(modifier = Modifier.height(35.dp))
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Update") {
            viewModel.handleUpdate(context = context,userModel= userModel)
            navHostController.currentBackStackEntry?.arguments?.putInt("id",userModel.id!!)
            navHostController.navigate("profile")
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun formLoginScreen(viewModel: AuthenViewModel, stateError: Boolean,userModel: UserModel) {
    if(userModel.name != null){
        textFieldLoginComponent("Your name", viewModel.userName.value, isError = stateError, textPlace = userModel.name) {
            viewModel.updateUserName(it)
        }
    }
    else{
        textFieldLoginComponent("Your name", viewModel.userName.value, isError = stateError) {
            viewModel.updateUserName(it)
        }
    }
    if(userModel.address != null){
        textFieldLoginComponent("Your address", viewModel.userAddress.value, isError = stateError,textPlace = userModel.address) {
            viewModel.updateUserAddress(it)
        }
    }
    else{
        textFieldLoginComponent("Your address", viewModel.userAddress.value, isError = stateError) {
            viewModel.updateUserAddress(it)
        }
    }
    if(userModel.phone != null){
        textFieldLoginComponent("Your phone", viewModel.userPhone.value, isError = stateError,textPlace = userModel.phone) {
            viewModel.updateUserPhone(it)
        }
    }
    else{
        textFieldLoginComponent("Your phone", viewModel.userPhone.value, isError = stateError) {
            viewModel.updateUserPhone(it)
        }
    }



}
