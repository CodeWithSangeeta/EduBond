package com.practice.edubond.app_navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.practice.edubond.feature.auth.state.AuthUiState
import com.practice.edubond.feature.auth.state.AuthViewModel
import com.practice.edubond.feature.auth.navigation.AuthRoutes
import com.practice.edubond.feature.auth.navigation.authNavGraph
import com.practice.edubond.feature.auth.state.UserRole
import com.practice.edubond.feature.student.navigation.StudentRoutes
import com.practice.edubond.feature.student.navigation.studentNavGraph
import com.practice.edubond.feature.teacher.navigation.TeacherRoutes
import com.practice.edubond.feature.teacher.navigation.teacherNavGraph
import com.practice.edubond.ui.theme.ThemeViewModel

@Composable
fun MainNavGraph(
    authViewModel: AuthViewModel,
    themeViewModel: ThemeViewModel
) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()

    NavHost(navController = navController,
        startDestination = MainRoutes.AUTH, builder ={

        //auth graph
        navigation(
            startDestination = AuthRoutes.LOGIN,
            route = MainRoutes.AUTH
        ) {
            authNavGraph(navController,authViewModel)
        }


        //  Student graph
        navigation(
            startDestination = StudentRoutes.STUDENT_DASHBOARD,
            route = MainRoutes.STUDENT_DASHBOARD
        ) {
           studentNavGraph(navController,authViewModel,themeViewModel)
        }

        // Teacher graph
        navigation(
            startDestination = TeacherRoutes.TEACHER_DASHBOARD,
            route = MainRoutes.TEACHER_DASHBOARD
        ) {
            teacherNavGraph(navController)
        }


    })


    LaunchedEffect(authState) {
        when (val state = authState) {
            is AuthUiState.Authenticated -> {
                val destination = when (state.role) {
                    UserRole.STUDENT -> MainRoutes.STUDENT_DASHBOARD
                    UserRole.TEACHER -> MainRoutes.TEACHER_DASHBOARD
                }

                    navController.navigate(destination) {
                        popUpTo(MainRoutes.AUTH) { inclusive = true }
                        launchSingleTop = true
                    }
            }

            AuthUiState.Unauthenticated -> {
                    navController.navigate(MainRoutes.AUTH) {
                        popUpTo(0)
                        launchSingleTop = true
                    }
            }

            AuthUiState.Loading -> {
                Log.d("MAIN_NAV", "Loading state")

            }

            is AuthUiState.Error -> {
                Log.d("MAIN_NAV", "Error state")
                navController.navigate(MainRoutes.AUTH) {
                    popUpTo(0)
                    launchSingleTop = true
                }
            }
        }
    }
}