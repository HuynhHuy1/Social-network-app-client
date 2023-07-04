package com.example.socialnetwork.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.*

@Composable
fun btnLoginComponent(backgroundCorlor: Brush, textContent: String, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = onClick,
        modifier = Modifier.padding(top = 25.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = backgroundCorlor, shape = RoundedCornerShape(13.dp)
                )
                .height(65.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = textContent,
                fontSize = 20.sp,
                style = buttonStyleWhite
            )
        }
    }
}

@Composable
fun loginLogoComponent() {
    val image = painterResource(id = R.drawable.logo)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .size(80.dp, 80.dp)
    )
}

@Composable
fun logoComponent() {
    val image = painterResource(id = R.drawable.logo)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .size(50.dp, 50.dp)
    )
}

@Composable
fun iconLoginComponent(resources: Int) {
    val icUser = painterResource(resources)
    Icon(
        painter = icUser,
        contentDescription = null,
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun textFieldLoginComponent(textLabel: String, modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 35.dp)
            .height(65.dp),
        textStyle = nomarStyle,
        label = { Text(text = textLabel) },
    )
}

@Composable
fun textFieldPassword(
    value: String,
    textLabel: String,
    modifier: Modifier = Modifier,
    onChange: (text : String) -> Unit
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = value,
        onValueChange =  onChange ,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 35.dp)
            .height(65.dp),
        textStyle = nomarStyle,
        label = { Text(text = textLabel) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(id = R.drawable.icon_invisible)
            else painterResource(id = R.drawable.icon_visibilie)
            IconButton(onClick ={passwordVisible = !passwordVisible} ) {
                Icon(painter = image, contentDescription = null,Modifier.size(25.dp))
            }
        }
    )
}

@Composable
fun textAnnotate(textHighLight: String, textNomar: String, onClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        append(textNomar)
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = textColor,
            ),
        ) {
            append(textHighLight)
        }
    }
    Text(text = annotatedString, Modifier
        .padding(top = 20.dp)
        .clickable { onClick() })
}

@Composable
fun textTitle(text: String, columnScope: ColumnScope, style: TextStyle) {
    columnScope.run {
        Text(
            modifier = Modifier
                .padding(top = 25.dp)
                .align(Alignment.Start),
            text = text,
            style = style,
        )
    }
}