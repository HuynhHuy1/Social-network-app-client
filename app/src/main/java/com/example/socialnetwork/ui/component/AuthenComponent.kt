package com.example.socialnetwork.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.*

@Composable
fun btnLoginComponent(backgroundCorlor: Brush, textContent: String,modifier: Modifier= Modifier, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = onClick,
        modifier = modifier.padding(top = 25.dp)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun textFieldLoginComponent(
    textLabel: String,
    text: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    textPlace : String? = null,
    onChange: (String) -> Unit,
) {
    if (isError) {
        OutlinedTextField(
            value = text,
            onValueChange = onChange,
            modifier = modifier
                .padding(top = 35.dp)
                .fillMaxWidth()
                .height(65.dp),
            textStyle = nomarStyle,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Red,
                unfocusedLabelColor = Color.Red
            ),
            label = { Text(text = textLabel) },
        )
        Spacer(modifier = Modifier.height(15.dp))
        if (text.equals("")) {
            Text(text = "${textLabel} is required", textAlign = TextAlign.Start, color = Color.Red)
        } else {
            Text(text = "${textLabel} is not valid")
        }
    } else {
        OutlinedTextField(
            value = text,
            onValueChange = onChange,
            modifier = modifier
                .padding(top = 35.dp)
                .fillMaxWidth()
                .height(65.dp),
            textStyle = nomarStyle,
            placeholder = {
                if (textPlace != null) {
                    Text(text = textPlace)
                }
            },
            label = { Text(text = textLabel) },
        )
    }

}

@Composable
fun textFieldPassword(
    value: String,
    textLabel: String,
    modifier: Modifier = Modifier,
    isError: Boolean,
    onChange: (String) -> Unit
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    if (isError) {
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 35.dp)
                .height(65.dp),
            textStyle = nomarStyle,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Red,
                unfocusedLabelColor = Color.Red
            ),
            label = { Text(text = textLabel) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.icon_invisible)
                else painterResource(id = R.drawable.icon_visibilie)
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, contentDescription = null, Modifier.size(25.dp))
                }
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        if (value.equals("")) {
            Text(text = "${textLabel} is required", textAlign = TextAlign.Start, color = Color.Red)
        }
    } else {
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
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
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, contentDescription = null, Modifier.size(25.dp))
                }
            }
        )
    }

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



@Composable
fun SuccessScreen(navHostController: NavHostController,@DrawableRes image : Int, textLabel: String,router : String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        var image = painterResource(image)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        var image_white = painterResource(id =R.drawable.img)
        Image(painter = image_white,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth()
        )
        btnLoginComponent(backgroundCorlor = loginBackgroundGradient,
            textContent = textLabel,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp, start = 25.dp, end = 25.dp)) {
            navHostController.navigate(router)
        }
    }
}