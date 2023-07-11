package com.example.socialnetwork.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.component.textTitle
import com.example.socialnetwork.ui.theme.*
import com.example.socialnetwork.view_model.AuthenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(
    navHostController: NavHostController,
    viewModel: AuthenViewModel = viewModel()
) {
    var stateError by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        textTitle(text = "Forgot password", columnScope = this@Column, style = titleStyle)
        textTitle(
            text = "Select which contact details should we user to reset your password",
            columnScope = this@Column,
            style = nomarStyle
        )
        Spacer(modifier = Modifier.height(50.dp))
        if(stateError){
            TextField(
                value = viewModel.email.value,
                onValueChange = { newValue ->
                    viewModel.updateEmail(newValue)
                },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
                placeholder = { Text(text = "Enter your email") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(android.graphics.Color.parseColor("#F3F2F8")),
                    errorIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Red,
                    unfocusedLabelColor = Color.Red,
                    ),
                shape = Shapes.large,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(1f, 1.3f),
            )
        }
        else{
            TextField(
                value = viewModel.email.value,
                onValueChange = { newValue ->
                    viewModel.updateEmail(newValue)
                },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
                placeholder = { Text(text = "Enter your email") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(android.graphics.Color.parseColor("#F3F2F8")),
                    errorIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = Shapes.large,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(1f, 1.3f),
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = " Continue ") {
            GlobalScope.launch(Dispatchers.Main) {
                if (viewModel.forgotPassword()) {
                    navHostController.navigate("verify_number")
                }
                else{
                    stateError = true
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        if(stateError){
            Text(text = "Error", color = Color.Red)
        }

    }
}

