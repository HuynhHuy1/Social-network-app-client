package com.example.socialnetwork.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialnetwork.R

@Preview(showSystemUi = true)
@Composable
fun IntroScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val image = painterResource(id = R.drawable.intro)
        Image(
            painter = image,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Hello",
            contentScale = ContentScale.FillBounds
        )
    }
}
@Composable
fun Intro2Screen(skip : () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        val image = painterResource(id = R.drawable.intro2)
        Image(
            painter = image,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Hello",
            contentScale = ContentScale.FillBounds
        )
        OutlinedButton(
            modifier = Modifier
                .background(color = Color.Transparent, shape = CircleShape)
                .padding(horizontal = 10.dp, vertical = 30.dp)
                .align(Alignment.TopEnd),
            border = BorderStroke(0.dp, color = Color.Transparent) ,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            onClick = skip
        ) {
            Text(
                text = "Skip",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .background(color = Color.Transparent)
            )
        }
    }
}
@Composable
fun Intro3Screen(skip : () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        val image = painterResource(id = R.drawable.intro3)
        Image(
            painter = image,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Hello",
            contentScale = ContentScale.FillBounds
        )
        OutlinedButton(
            modifier = Modifier
                .background(color = Color.Transparent, shape = CircleShape)
                .padding(horizontal = 10.dp, vertical = 30.dp)
                .align(Alignment.TopEnd),
            border = BorderStroke(0.dp, color = Color.Transparent) ,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            onClick = skip
        ) {
            Text(
                text = "Skip",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .background(color = Color.Transparent)
            )
        }
    }
}