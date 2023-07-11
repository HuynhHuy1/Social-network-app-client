package com.example.socialnetwork.ui.screen

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
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
import com.example.socialnetwork.view_model.AuthenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun VerifyNumberScreen(
    navHostController: NavHostController,
    viewModel: AuthenViewModel = viewModel()
) {
    var context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var otpValues = remember { mutableStateListOf("", "", "", "") }
        textTitle(text = "Enter verify code", columnScope = this@Column, style = titleStyle)
        textTitle(
            text = "We have sent the code verification to your email",
            columnScope = this@Column,
            style = nomarStyle
        )
        Spacer(modifier = Modifier.height(40.dp))
        OtpChar(otpValues)
        Spacer(modifier = Modifier.height(20.dp))
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient, textContent = "Verify") {
            GlobalScope.launch(Dispatchers.Main) {
                if (!otpValues.equals("")) {
                    var numberKey = otpValues.let {
                        it.joinToString("")
                    }
                    if (viewModel.checkNumberKey(numberKey.let{ it.toInt() }, context)) {
                        navHostController.navigate("reset_password")
                    }
                }
            }
        }
    }
}
    @Composable
    fun OtpChar(otpValues: SnapshotStateList<String>) {
        val maxChar = 1
        LazyRow(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(4) { index ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = otpValues[index],
                        onValueChange = {
                            if (it.length <= maxChar) {
                                otpValues[index] = it
                            }
                        },
                        modifier = Modifier
                            .size(70.dp)
                            .border(width = 1.dp, color = textColor, Shapes.medium),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Black,
                            backgroundColor = Transparent,
                            unfocusedIndicatorColor = Transparent,
                            focusedIndicatorColor = Transparent
                        )
                    )
                    Divider(
                        Modifier
                            .width(28.dp)
                            .padding(bottom = 2.dp)
                            .offset(y = -10.dp),
                        color = White,
                        thickness = 1.dp
                    )
                }
            }
        }

    }
