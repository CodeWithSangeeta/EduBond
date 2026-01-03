package com.practice.edubond.app_navigation

import com.practice.edubond.feature.student.StudentHomeScreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.practice.edubond.feature.auth.login.LoginScreen
import com.practice.edubond.feature.auth.navigation.AuthRoutes
import com.practice.edubond.feature.auth.signup.SignupScreen
import com.practice.edubond.feature.teacher.TeacherHomeScreen

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainRoutes.AUTH, builder ={

        navigation(
            startDestination = AuthRoutes.LOGIN,
            route = MainRoutes.AUTH
        ) {
            composable(AuthRoutes.LOGIN) {
                LoginScreen(navController)
            }
            composable(AuthRoutes.SIGNUP) {
             //   SignupScreen(navController)
            }
        }


    })
}