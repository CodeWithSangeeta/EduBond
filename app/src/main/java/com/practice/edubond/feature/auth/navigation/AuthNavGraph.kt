package com.practice.edubond.feature.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practice.edubond.app_navigation.MainRoutes
import com.practice.edubond.feature.auth.login.LoginScreen
import com.practice.edubond.feature.auth.signup.SignupScreen
import com.practice.edubond.feature.student.StudentHomeScreen
import com.practice.edubond.feature.teacher.TeacherHomeScreen

@Composable
fun AuthNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainRoutes.AUTH, builder ={
        composable(AuthRoutes.LOGIN){
            LoginScreen(navController)
        }
        composable(AuthRoutes.SIGNUP){
          //  SignupScreen(navController )
        }
    })
}