package com.practice.edubond.app_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.practice.edubond.feature.auth.state.AuthUiState
import com.practice.edubond.feature.auth.state.AuthViewModel
import com.practice.edubond.feature.auth.navigation.AuthRoutes
import com.practice.edubond.feature.auth.navigation.authNavGraph
import com.practice.edubond.feature.student.navigation.StudentRoutes
import com.practice.edubond.feature.student.navigation.studentNavGraph
import com.practice.edubond.feature.teacher.navigation.TeacherRoutes
import com.practice.edubond.feature.teacher.navigation.teacherNavGraph

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()

    NavHost(navController = navController, startDestination = MainRoutes.AUTH, builder ={

        //auth graph
        navigation(
            startDestination = AuthRoutes.LOGIN,
            route = MainRoutes.AUTH
        ) {
            authNavGraph(navController)
        }


        //  Student graph
        navigation(
            startDestination = StudentRoutes.HOME,
            route = MainRoutes.STUDENT_HOME
        ) {
           studentNavGraph(navController)
        }

        // Teacher graph
        navigation(
            startDestination = TeacherRoutes.HOME,
            route = MainRoutes.TEACHER_HOME
        ) {
            teacherNavGraph(navController)
        }


    })

    val currentRoute = navController.currentBackStackEntry?.destination?.route


    LaunchedEffect(authState) {
        when (authState) {
            is AuthUiState.Authenticated -> {
                val role = (authState as AuthUiState.Authenticated).role

                val destination = when (role) {
                    "STUDENT" -> MainRoutes.STUDENT_HOME
                    "TEACHER" -> MainRoutes.TEACHER_HOME
                    else -> MainRoutes.AUTH
                }

                val currentRoute =
                    navController.currentBackStackEntry?.destination?.route

                if (currentRoute != destination) {
                    navController.navigate(destination) {
                        popUpTo(MainRoutes.AUTH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }

            AuthUiState.Unauthenticated -> {
                val currentRoute =
                    navController.currentBackStackEntry?.destination?.route

                if (currentRoute != MainRoutes.AUTH) {
                    navController.navigate(MainRoutes.AUTH) {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            }

            AuthUiState.Loading -> {
                // 🔹 Do nothing or show splash
            }

            is AuthUiState.Error -> {
                // 🔹 Optional: log or force logout
                navController.navigate(MainRoutes.AUTH) {
                    popUpTo(0)
                    launchSingleTop = true
                }
            }
        }
    }
}