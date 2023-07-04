package com.example.socialnetwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.socialnetwork.ui.component.Navbar
import com.example.socialnetwork.ui.navigation.HostNavigation
import com.example.socialnetwork.ui.theme.SocialNetworkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialNetworkTheme {
                MyHomeScreen()
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyHomeScreen(){
        val navController = rememberNavController()
        var showNavbar by remember { mutableStateOf(true) }
        var listRouterAuthen = listOf("intro1","intro2","intro3","intro_login","login","signup","forgot_password","verify_number","reset_password")
        Scaffold(
            content = {HostNavigation(navController = navController)},
            bottomBar ={
                if(!showNavbar){
                    Navbar(navController)
                }
            }
        )
        LaunchedEffect(navController) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                showNavbar = listRouterAuthen.contains(destination.route)
            }
        }
    }
}