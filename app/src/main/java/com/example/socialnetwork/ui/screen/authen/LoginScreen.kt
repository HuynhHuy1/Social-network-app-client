package com.example.socialnetwork.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.component.*
import com.example.socialnetwork.ui.theme.gradientStyle
import com.example.socialnetwork.ui.theme.loginBackgroundGradient
import com.example.socialnetwork.ui.theme.nomarStyle
import kotlin.math.log

@Composable
fun LoginScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        loginLogoComponent()
        Text(
            modifier = Modifier.padding(top = 25.dp),
            text = "Log in to make your memories",
            style = nomarStyle
        )
        textFieldLoginComponent("Email or your phone")
        var valuePassword by remember {
            mutableStateOf("")
        }
        textFieldPassword(value = valuePassword, textLabel = "Password") {
             valuePassword = it
        }
        Text(
            modifier = Modifier
                .padding(top = 15.dp)
                .align(Alignment.End)
                .clickable {
                    navHostController.navigate("forgot_password")
                },
            text = "Forgot Password?",
            style = gradientStyle,

        )
        btnLoginComponent(
            backgroundCorlor = loginBackgroundGradient,
            textContent = "Log in",
            onClick ={ handleLoginBtn(navHostController) }
        )
        Box(modifier = Modifier.size(20.dp))
        textAnnotate(textHighLight = "Sign up", textNomar = "Dont have an account? ", onClick = {handleSignupText(navHostController)})
    }
}

fun handleSignupText(navHostController : NavHostController){
    navHostController.navigate("signup")
}
fun handleLoginBtn(navHostController: NavHostController){
    navHostController.navigate("home")
}
