package com.example.socialnetwork.ui.screen

import android.widget.Space
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.socialnetwork.ui.component.btnLoginComponent
import com.example.socialnetwork.ui.component.textFieldLoginComponent
import com.example.socialnetwork.ui.component.textTitle
import com.example.socialnetwork.ui.theme.*


@Composable
fun VerifyNumberScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        textTitle(text = "Enter verify code", columnScope = this@Column, style = titleStyle)
        textTitle(
            text = "We have sent the code verification to your email",
            columnScope = this@Column,
            style = nomarStyle
        )
        Spacer(modifier = Modifier.height(20.dp))
        textFieldLoginComponent(textLabel = "Enter OTP code")
        Spacer(modifier = Modifier.height(20.dp))
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Verify" ) {
            navHostController.navigate("reset_password")
        }

    }
}

