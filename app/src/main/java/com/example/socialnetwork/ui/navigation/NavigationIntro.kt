package com.example.socialnetwork.ui.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialnetwork.model.PostModel
import com.example.socialnetwork.model.UserModel
import com.example.socialnetwork.ui.screen.*
import com.example.socialnetwork.ui.screen.authen.PasswordSuccessScreen
import com.example.socialnetwork.ui.screen.authen.ResetPasswordScreen
import com.example.socialnetwork.ui.screen.home.*
import com.example.socialnetwork.util.TokenManagerUtil
import com.squareup.moshi.Moshi
import kotlinx.coroutines.delay

@Composable
fun HostNavigation(navController: NavHostController,context : Context) {

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
            IntroLogin(
                signupBtnOnClick = { navController.navigate("signup") },
                navHostController = navController
            )
        }
        composable("login") {
            LoginScreen(
                navHostController = navController
            )
        }
        composable("signup") {
            signupScreen(navHostController = navController)
        }
        composable("signup_success") {
            SignupSuccessScreen(navController)
        }
        composable("forgot_password") {
            ForgotPasswordScreen(navController)
        }
        composable("verify_number") {
            VerifyNumberScreen(navController)
        }
        composable("reset_password") {
            ResetPasswordScreen(navHostController = navController)
        }
        composable("password_success") {
            PasswordSuccessScreen(navController)
        }
        composable("home") {
            HomeScreen(navHostController = navController)
        }
        composable("post_detail") { backStackEntry ->
            val postModel = navController.previousBackStackEntry?.arguments?.get("postModel")
            if (postModel != null) {
                DetailPostScreen(postModel = postModel as PostModel, navHostController = navController)
            }
        }
        composable("search") {
            SearchScreen(navHostController = navController)
        }
        composable("post")
        {
            UploadPostScreen(navHostController = navController)
        }
        composable("profile_user")
        {
            val id = TokenManagerUtil(context).getID()
            Log.d("TAG", "HostNavigation uhashcxz: $id")
            ProfileScreen(userId = id,navController)
        }
        composable("profile")
        {
            val id = navController.previousBackStackEntry?.arguments?.get("id")
            if(id !=null){
                ProfileScreen(userId = id as Int,navController)
            }
            else{
                Log.d("TAG", "HostNavigation: Khong co")
            }
        }
        composable("update_info"){
            val userModel = navController.previousBackStackEntry?.arguments?.get("userModel")
            if(userModel != null){
                updateInfoScreen(userModel = userModel as UserModel, navHostController = navController)
            }
        }
    }
}