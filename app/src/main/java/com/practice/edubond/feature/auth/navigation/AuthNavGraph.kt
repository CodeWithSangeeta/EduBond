package com.practice.edubond.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.practice.edubond.feature.auth.login.LoginScreen
import com.practice.edubond.feature.auth.signup.SignupScreen
import com.practice.edubond.feature.auth.state.AuthViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel
)
{
composable(route = AuthRoutes.LOGIN) {
    LoginScreen(
        navController = navController,
        onLoginSuccess = { userId, role ->
            authViewModel.onLoginSuccess(userId, role)
        }
    )
}
composable(route = AuthRoutes.SIGNUP) {
    SignupScreen(
        navController = navController,
        onSignupSuccess = { userId, role ->
            authViewModel.onSignupSuccess(userId, role)
        }
    )
}


}