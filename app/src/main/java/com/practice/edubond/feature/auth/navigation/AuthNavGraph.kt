package com.practice.edubond.feature.auth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practice.edubond.feature.auth.login.LoginScreen
import com.practice.edubond.feature.auth.signup.SignupScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController)
{
composable(route = AuthRoutes.LOGIN) {
    LoginScreen(navController)
}
composable(route = AuthRoutes.SIGNUP) {
     SignupScreen(navController)
}


}