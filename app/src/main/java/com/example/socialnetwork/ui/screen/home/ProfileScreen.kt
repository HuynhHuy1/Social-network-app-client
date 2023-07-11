package com.example.socialnetwork.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.Display.Mode
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.model.UserModel
import com.example.socialnetwork.ui.component.Navbar
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.theme.*
import com.example.socialnetwork.util.TokenManagerUtil
import com.example.socialnetwork.view_model.HomeViewModel
import com.example.socialnetwork.view_model.ProfileViewModel
import convertBase64ToImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(userId: Int, navHostController: NavHostController) {
    val context = LocalContext.current
    val profileViewModel = remember { ProfileViewModel() }
    LaunchedEffect(Unit) {
        profileViewModel.getUserProfile(context = context, userID = userId)
        profileViewModel.getImageProfile(context = context, userID = userId)
    }
    val userDto by profileViewModel.userDto
    val listImage by profileViewModel.listImage.collectAsState()
    val isFollowing by profileViewModel.isFolowing

    Log.d("TAG", "ProfileScreen11: ${isFollowing}")

    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()){
            var image: ImageBitmap? = userDto.let {
                it.avatar?.let { it1 ->
                    userDto.avatar?.let { it2 -> convertBase64ToImageBitmap(it2) }
                }
            }
            if (image != null) {
                Image(
                    bitmap = image,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .size(100.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds

                )
            } else {
                val image = ImageBitmap.imageResource(id = R.drawable.avatar_null)
                Image(
                    bitmap = image,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .size(100.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Icon(painter = painterResource(id = R.drawable.logout),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .size(20.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        profileViewModel.handleLogout(context)
                        navHostController.navigate("login")
                    })}
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = userDto.name.toString(), style = titleStyle)
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            TextFolow(number = userDto.follower.toString(), text = "Followers")
            Spacer(modifier = Modifier.width(70.dp))
            TextFolow(number = userDto.following.toString(), text = "Following")
        }
        Spacer(modifier = Modifier.height(25.dp))
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            var idUserLogin =TokenManagerUtil(context).getID()
            btnFollowOrEdit(isUserLogin = idUserLogin == userId,isFollowing = isFollowing, handleOnClick = {
                GlobalScope.launch(Dispatchers.Main){
                    profileViewModel.handleOnClickFollow(userID = userId,context=context)
                }
            },navHostController = navHostController, userModel = userDto)
        }
        Spacer(modifier = Modifier.height(25.dp))
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalItemSpacing = 5.dp
        ) {
            items(listImage) {
                var image: ImageBitmap? = convertBase64ToImageBitmap(it.image.toString())
                if (image != null) {
                    Image(
                        bitmap = image!!,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(Shapes.small)
                            .clickable {
                                navHostController.currentBackStackEntry?.arguments?.putParcelable(
                                    "postModel", it
                                )
                                navHostController.navigate("post_detail")
                            },
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
        }

    }
}

@Composable
fun TextFolow(number: String, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = number, style = LargeStyle)
        Text(text = text, style = textContentShadowStyle)
    }
}

@Composable
fun btnFollowOrEdit(isUserLogin: Boolean,isFollowing : Boolean,handleOnClick :() -> Unit, navHostController: NavHostController,userModel: UserModel) {
    if (isUserLogin) {
        btnLoginComponent(
            backgroundCorlor = loginBackgroundGradient,
            textContent = "Edit profile"
        ) {
            navHostController.currentBackStackEntry?.arguments?.putParcelable(
                "userModel",userModel
            )
            navHostController.navigate("update_info")
        }
    } else {
        if(isFollowing){
            btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Following", onClick = handleOnClick)
        }
        else{
            btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Follow", onClick = handleOnClick)
        }
    }
}
