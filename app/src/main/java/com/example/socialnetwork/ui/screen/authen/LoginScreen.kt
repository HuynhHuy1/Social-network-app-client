package com.example.socialnetwork.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.socialnetwork.ui.component.*
import com.example.socialnetwork.ui.theme.*
import com.example.socialnetwork.view_model.AuthenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    loginViewModel: AuthenViewModel = viewModel()
) {
    var context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var stateError by remember {
            mutableStateOf(false)
        }


        loginLogoComponent()
        Text(
            modifier = Modifier.padding(top = 25.dp),
            text = "Log in to make your memories",
            style = nomarStyle
        )
            textFieldLoginComponent(
                textLabel = "Email or your phone",
                text = loginViewModel.email.value,
                isError = stateError,
            ) {
                loginViewModel.updateEmail(it)
                stateError = false
            }

        textFieldPassword(
            value = loginViewModel.password.value,
            textLabel = "Password",
            isError = stateError,
        ) {
            loginViewModel.updatePassword(it)
            stateError = false
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
            textContent = "Login",
            onClick = {
                GlobalScope.launch(Dispatchers.Main) {
                    var reponseModel = loginViewModel.login(context)
                    if(reponseModel.status.equals("Success")){
                        navHostController.navigate("home")
                    }
                    else{
                        stateError = true
                    }
                }
            }
        )
        Box(modifier = Modifier.size(20.dp))
        textAnnotate(
            textHighLight = "Sign up",
            textNomar = "Don't have an account? ",
            onClick = { handleSignupText(navHostController) })
    }
}
fun handleSignupText(navHostController: NavHostController) {
    navHostController.navigate("signup")
}

