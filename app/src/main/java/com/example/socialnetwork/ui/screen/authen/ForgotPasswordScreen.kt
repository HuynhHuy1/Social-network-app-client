package com.example.socialnetwork.ui.screen

import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.component.textFieldLoginComponent
import com.example.socialnetwork.ui.component.textTitle
import com.example.socialnetwork.ui.theme.*

@Composable
fun ForgotPasswordScreen(navHostController: NavHostController) {
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
        var value by remember { mutableStateOf("") }
        TextField(
            value = value,
            onValueChange = { newValue ->
                value = newValue
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
        Spacer(modifier = Modifier.height(30.dp))
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = " Continue " ) {
            navHostController.navigate("verify_number")
        }

    }
}

