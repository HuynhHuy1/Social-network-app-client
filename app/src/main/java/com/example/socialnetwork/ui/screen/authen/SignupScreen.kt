package com.example.socialnetwork.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.socialnetwork.ui.component.*
import com.example.socialnetwork.ui.theme.gradientStyle
import com.example.socialnetwork.ui.theme.loginBackgroundGradient
import com.example.socialnetwork.ui.theme.nomarStyle
import com.example.socialnetwork.ui.theme.titleStyle


@Composable
fun signupScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        textTitle(text = "Create New Account", columnScope = this@Column, style = titleStyle)
        textTitle(text = "Create New Account", columnScope = this@Column, style = nomarStyle)
        textFieldLoginComponent("Your name")
        textFieldLoginComponent("Your email")
        var valuePassword by remember {
            mutableStateOf("")
        }
        textFieldPassword(value =valuePassword  , textLabel ="Enter new password" ){
            valuePassword = it
        }
        var valueRePassword by remember {
            mutableStateOf("")
        }
        textFieldPassword(value = valueRePassword, textLabel = "Re-enter password"){
            valueRePassword = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Sign up", onClick = {})
        textAnnotate(textHighLight = "Sign in ", textNomar = "Already have an account? ") {
            navHostController.navigate("login")
        }
    }
}