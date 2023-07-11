package com.example.socialnetwork.ui.screen.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.Shapes
import com.example.socialnetwork.ui.theme.backgroundPost
import com.example.socialnetwork.ui.theme.nomarStyle
import com.example.socialnetwork.ui.theme.searchStyle
import com.example.socialnetwork.view_model.HomeViewModel

import com.example.socialnetwork.view_model.SearchViewModel
import convertBase64ToImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),navHostController: NavHostController) {
    val context = LocalContext.current
    val listUser by viewModel.listUserSearch.collectAsState()
    Log.d("TAG", "DetailPostScreen: ${listUser.size}")
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
    ) {
        item {
            TextFieldSearch(
                textLabel = "Search",
                context = context,
                modifier = Modifier.height(60.dp),
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(30.dp))

        }
        items(listUser) {
            Log.d("TAG", "SearchScreen: ${it}")
            var image: ImageBitmap? = it.let {
                it.avatar?.let { it1 ->
                    it.avatar?.let { it2 -> convertBase64ToImageBitmap(it2) }
                }
            }
            if (image != null) {
                UserSearch(image = image, nameUser = it.name!!,navHostController = navHostController, id = it.id!!)
            }
            else{
                var image = ImageBitmap.imageResource(id = R.drawable.avatar_null)
                UserSearch(image = image, nameUser = it.name!!, navHostController = navHostController,id = it.id!! )
            }
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

@Composable
fun TextFieldSearch(
    textLabel: String, viewModel: SearchViewModel, context: Context, modifier: Modifier = Modifier
) {
    TextField(value = viewModel.contentSearch.value,
        onValueChange = {
            viewModel.updateContentSearch(it)
        },
        modifier = modifier.fillMaxWidth(),
        textStyle = searchStyle,
        placeholder = { Text(text = textLabel, style = searchStyle) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundPost,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        shape = Shapes.medium,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier.clickable {
                    GlobalScope.launch(Dispatchers.Main) {
                        viewModel.searchUser(context)
                    }
                },
            )
        })
}

