package com.example.socialnetwork.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.backgroundNavbarBlur

@Composable
fun Navbar(navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = backgroundNavbarBlur)
            .zIndex(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconCustom(icon = R.drawable.icon_home,"home",navHostController)
        IconCustom(icon = R.drawable.icon_search,"search",navHostController)
        IconCustom(icon = R.drawable.icon_union,"post",navHostController)
        IconCustom(icon = R.drawable.user,"profile_user",navHostController)
    }
}

@Composable
fun IconCustom(@DrawableRes icon: Int, router: String,navHostController: NavHostController) {
    val iconPainter = painterResource(id = icon)
    Image(
        painter = iconPainter,
        contentDescription = null,
        modifier = Modifier
            .size(30.dp)
            .graphicsLayer()
            .clickable { handleIconOnClick(router, navHostController) },
    )
}

@Composable
fun IconAvatar(avatar : ImageBitmap?, router: String,navHostController: NavHostController) {
    if (avatar != null) {
        Image(
            bitmap = avatar,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .graphicsLayer()
                .clickable { handleIconOnClick(router, navHostController) },
        )
    }
}
fun handleIconOnClick(router: String, navHostController: NavHostController){
    navHostController.navigate(router)
}