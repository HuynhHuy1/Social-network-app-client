package com.example.socialnetwork.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.socialnetwork.ui.component.*

import com.example.socialnetwork.ui.theme.loginBackgroundGradient
import com.example.socialnetwork.ui.theme.nomarStyle
import com.example.socialnetwork.ui.theme.titleStyle
import com.example.socialnetwork.view_model.AuthenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.socialnetwork.R
import com.example.socialnetwork.model.ReponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun signupScreen(
    navHostController: NavHostController,
    viewModel: AuthenViewModel = viewModel()
) {
    var stateError by remember {
        mutableStateOf(false)
    }
    var textError by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        textTitle(text = "Create New Account", columnScope = this@Column, style = titleStyle)
        textTitle(text = "Create New Account", columnScope = this@Column, style = nomarStyle)
        textFieldLoginComponent("Your name", viewModel.userName.value, isError = stateError) {
            viewModel.updateUserName(it)
            stateError = false
        }
        textFieldLoginComponent("Your email", viewModel.email.value, isError = stateError) {
            viewModel.updateEmail(it)
            stateError = false

        }
        textFieldPassword(value = viewModel.password.value, textLabel = "Enter new password",isError = stateError) {
            viewModel.updatePassword(it)
            stateError = false

        }
        textFieldPassword(value = viewModel.rePassword.value, textLabel = "Re-enter password",isError = stateError) {
            viewModel.updateRePassword(it)
            stateError = false
        }
        Spacer(modifier = Modifier.height(20.dp))
        btnLoginComponent(
            backgroundCorlor = loginBackgroundGradient,
            textContent = "Sign up",
            onClick = {
                GlobalScope.launch(Dispatchers.Main) {
                    var response : ReponseModel = viewModel.signup(context)
                    if(response.status.equals("Success")){
                        navHostController.navigate("signup_success")
                    }
                    else{
                        stateError = true
                        textError = response.message.toString()
                    }
                }
                })
        textAnnotate(textHighLight = "Sign in ", textNomar = "Already have an account? ") {
            navHostController.navigate("login")
        }

    }
}
@Composable
fun SignupSuccessScreen(navHostController: NavHostController){
    SuccessScreen(navHostController = navHostController, image = R.drawable.signup_success, textLabel = "Get Started","home")
}