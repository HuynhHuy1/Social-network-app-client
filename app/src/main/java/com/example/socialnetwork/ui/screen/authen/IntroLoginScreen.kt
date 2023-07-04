package com.example.socialnetwork.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.component.textAnnotate
import com.example.socialnetwork.ui.theme.*

@Composable
fun IntroLogin(signupBtnOnClick: () -> Unit, navHostController: NavHostController) {
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(top = 150.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = painterResource(id = R.drawable.login)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.size(326.76.dp, 282.74.dp)
        )
        Spacer(modifier = Modifier.height(180.dp))
        btnLoginComponent(
            backgroundCorlor = loginBackgroundGradient,
            textContent = "Login with email or phone number ",
            onClick = {
                navHostController.navigate("login")
            })
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            contentPadding = PaddingValues(),
            onClick = signupBtnOnClick,
            modifier = Modifier.padding(top = 25.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        loginBackground, shape = RoundedCornerShape(13.dp)
                    )
                    .height(65.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Sign up with email or phone number",
                    fontSize = 20.sp,
                    style = buttonStyle
                )
            }
        }
    }
}

