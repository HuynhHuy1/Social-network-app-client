package com.example.socialnetwork.ui.screen.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.component.Navbar

@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun SearchScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
    ){
        item {
            TextFieldComment(textLabel = "Search")
            Spacer(modifier = Modifier.height(30.dp))

        }
        items(5){
            HeaderPost(image = R.drawable.avatar_user, nameUser = "Huy")
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

