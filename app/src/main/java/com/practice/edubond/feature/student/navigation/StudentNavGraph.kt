package com.practice.edubond.feature.student.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.practice.edubond.feature.auth.state.AuthViewModel
import com.practice.edubond.feature.student.screens.StudentDashboard

fun NavGraphBuilder.studentNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
)
{
    composable(route = StudentRoutes.STUDENT_DASHBOARD) {
        StudentDashboard(navController)
    }
    composable(route = StudentRoutes.STUDENT_PROFILE) {

    }


}