package com.example.socialnetwork.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.component.Navbar
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.theme.*



@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val avatar = painterResource(id = R.drawable.avatar_user)
        Image(
            painter = avatar,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .size(80.dp),
            contentScale = ContentScale.FillBounds

        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Mike Johnson", style = titleStyle)
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            TextFolow(number = "128", text = "Followers")
            Spacer(modifier = Modifier.width(70.dp))
            TextFolow(number = "580", text = "Following")
        }
        Spacer(modifier = Modifier.height(25.dp))
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            btnFollowOrEdit(isUserLogin = true)
        }
        Spacer(modifier = Modifier.height(25.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(20) {
                val imageContent = painterResource(id = R.drawable.image_post)
                Image(
                    painter = imageContent,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(Shapes.small),
                    contentScale = ContentScale.FillBounds
                )
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
fun btnFollowOrEdit(isUserLogin: Boolean) {
    if (isUserLogin) {
        btnLoginComponent(
            backgroundCorlor = loginBackgroundGradient,
            textContent = "Edit profile"
        ) {
        }
    } else {
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Follow") {
        }
    }
}
