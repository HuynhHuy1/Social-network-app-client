package com.example.socialnetwork.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// CustomColor
val textColor = Color(android.graphics.Color.parseColor("#7268DC"))
val loginBackground = Color(android.graphics.Color.parseColor("#EDEDED"))
val loginLine = Color(android.graphics.Color.parseColor("#D8D8D8")).copy(alpha = 0.6f)
val loginText = Color(android.graphics.Color.parseColor("#7268DC"))
val backgroundPost = Color(android.graphics.Color.parseColor("#F9F7F9"))
val backgroundNavbarBlur = Color(android.graphics.Color.parseColor("#FFFFFF"))


// Color Gradent

val startLoginBackground = Color(android.graphics.Color.parseColor("#6B65DE")) // Màu đỏ
val endLoginBackground = Color(android.graphics.Color.parseColor("#E89DE7"))
val loginBackgroundGradient = Brush.horizontalGradient(colors = listOf(startLoginBackground, endLoginBackground))
