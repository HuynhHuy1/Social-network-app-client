package com.example.socialnetwork.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.component.Navbar
import com.example.socialnetwork.ui.theme.*

@Composable
fun UploadPostScreen() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(top = 40.dp, start = 15.dp, end = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                ) {
                    Text(
                        text = " Create Post",
                        style = titleStyle,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }
                textFieldPost(textLabel = "What's on your mind ?")
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val iconCamera = painterResource(id = R.drawable.camera_solod)
                    Icon(painter = iconCamera,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    val iconImage = painterResource(id = R.drawable.image_solid)
                    Icon(painter = iconImage,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp))
                    Spacer(modifier = Modifier.width(200.dp))
                    buttonUpload()
                }
            }
}

@Composable
fun textFieldPost(textLabel: String) {
    var text by remember {
        mutableStateOf("")
    }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp)
            .height(300.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        shape = Shapes.medium,
        textStyle = contentStyle,
        placeholder = { Text(text = textLabel)},
    )
}

@Composable
fun buttonUpload(){
    Button(
        colors = ButtonDefaults.buttonColors(startLoginBackground),
        onClick = {},
    ) {
        Text(text = "Post", style = buttonStyleWhite )
    }
}