package com.example.socialnetwork.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialnetwork.ui.screen.*
import com.example.socialnetwork.ui.screen.authen.ResetPasswordScreen
import com.example.socialnetwork.ui.screen.home.*
import kotlinx.coroutines.delay

@Composable
fun HostNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "intro1") {
        composable("intro1") {
            IntroScreen()
            LaunchedEffect(true) {
                delay(1000)
                navController.navigate("intro2")
            }
        }
        composable("intro2") {
            Intro2Screen(skip = { navController.navigate("intro3") })
        }
        composable("intro3") {
            Intro3Screen(skip = { navController.navigate("intro_login") })
        }
        composable("intro_login") {
            IntroLogin(signupBtnOnClick = { navController.navigate("signup") }, navHostController =  navController)
        }
        composable("login") {
            LoginScreen(
                navHostController = navController
            )
        }
        composable("signup") {
            signupScreen(navHostController = navController)
        }
        composable("forgot_password"){
            ForgotPasswordScreen(navController)
        }
        composable("verify_number"){
            VerifyNumberScreen(navController)
        }
        composable("reset_password"){
            ResetPasswordScreen()
        }
        composable("home"){
            HomeScreen()
        }
        composable("home_comment"){
            DialogShowComment(navController)
        }
        composable("search"){
            SearchScreen()
        }
        composable("post")
        {
            UploadPostScreen()
        }
        composable("profile")
        {
            ProfileScreen()
        }
    }
}