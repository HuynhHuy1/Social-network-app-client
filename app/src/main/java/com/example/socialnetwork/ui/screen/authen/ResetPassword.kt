package com.example.socialnetwork.ui.screen.authen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.component.textFieldLoginComponent
import com.example.socialnetwork.ui.component.textFieldPassword
import com.example.socialnetwork.ui.component.textTitle
import com.example.socialnetwork.ui.theme.loginBackgroundGradient
import com.example.socialnetwork.ui.theme.nomarStyle
import com.example.socialnetwork.ui.theme.titleStyle

@Preview
@Composable
fun ResetPasswordScreen() {
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
        Spacer(modifier = Modifier.height(200.dp))
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Reset password" ) {

        }

    }
}
