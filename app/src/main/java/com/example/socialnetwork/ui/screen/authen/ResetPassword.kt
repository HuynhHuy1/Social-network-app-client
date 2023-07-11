package com.example.socialnetwork.ui.screen.authen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.component.SuccessScreen
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.component.textFieldPassword
import com.example.socialnetwork.ui.component.textTitle
import com.example.socialnetwork.ui.theme.loginBackgroundGradient
import com.example.socialnetwork.ui.theme.nomarStyle
import com.example.socialnetwork.ui.theme.titleStyle
import com.example.socialnetwork.view_model.AuthenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun ResetPasswordScreen(viewModel : AuthenViewModel = viewModel(),navHostController: NavHostController) {
    val context = LocalContext.current
    var stateError by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        textTitle(text = "Enter New Password", columnScope = this@Column, style = titleStyle)
        textTitle(
            text = "Create new password so you can share your memories again",
            columnScope = this@Column,
            style = nomarStyle
        )
        Spacer(modifier = Modifier.height(20.dp))
        textFieldPassword(value = viewModel.password.value , textLabel ="Enter new password" , isError = stateError){
            viewModel.updatePassword(it)
            stateError = false
        }
        textFieldPassword(value = viewModel.rePassword.value, textLabel = "Re-enter password",isError = stateError){
            viewModel.updateRePassword(it)
            stateError = false }
        Spacer(modifier = Modifier.height(200.dp))
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Reset password" ) {
            GlobalScope.launch(Dispatchers.Main) {
                if(viewModel.resetPassword(context)){
                    navHostController.navigate("password_success")
                }
                else{
                    stateError = true
                }
            }
        }
    }
}

@Composable
fun PasswordSuccessScreen(navHostController: NavHostController){
    SuccessScreen(navHostController = navHostController, image = R.drawable.passwork_success , textLabel = "Back Login", router = "Login" )
}
