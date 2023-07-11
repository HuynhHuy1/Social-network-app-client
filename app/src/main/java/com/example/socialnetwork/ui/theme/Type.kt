package com.example.socialnetwork.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
)
val buttonStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 18.sp,
    fontWeight = FontWeight.W500,
    color = Color.Black
)
val buttonStyleWhite= TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 18.sp,
    fontWeight = FontWeight.W600,
    color = Color.White
)
val subtitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 16.sp,
    fontWeight = FontWeight.W600,
    color = Color.Black
)

val searchStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 20.sp,
    fontWeight = FontWeight.W600,
    color = Color.Black
)


val gradientStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 18.sp,
    fontWeight = FontWeight.W400,
    color = textColor
)

val gradientSmallStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 16.sp,
    fontWeight = FontWeight.W400,
    color = textColor
)

val nomarStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 16.sp,
    fontWeight = FontWeight.W400,
    color = Color.Black
)
val MediumStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 14.sp,
    fontWeight = FontWeight.W500,
    color = Color.Black
)
val LargeStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 18.sp,
    fontWeight = FontWeight.W600,
    color = Color.Black
)
val contentStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 16.sp,
    fontWeight = FontWeight.W400,
    color = Color.Black
)

val titleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 20.sp,
    fontWeight = FontWeight.W800,
    color = Color.Black
)

val textContentShadowStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 14.sp,
    fontWeight = FontWeight.W400,
    color = Color(android.graphics.Color.parseColor("#8B8A94")).copy(alpha = 0.6f)
)