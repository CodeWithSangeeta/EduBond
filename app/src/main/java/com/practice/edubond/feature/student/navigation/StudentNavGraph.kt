package com.practice.edubond.feature.student.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.practice.edubond.feature.auth.state.AuthViewModel
import com.practice.edubond.feature.student.screens.StudentDashboard

fun NavGraphBuilder.studentNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    themeViewModel= ThemeViewModel
)
{
    composable(route = StudentRoutes.STUDENT_DASHBOARD) {
        StudentDashboard(navController, themeViewModel) { themeViewModel.toggleTheme() }
    }
    composable(route = StudentRoutes.STUDENT_BADGES) { StudentBadges(navController) }
    composable(route = StudentRoutes.STUDENT_PROGRESS) { StudentProgress(navController) }
    composable(route = StudentRoutes.STUDENT_SETTINGS) { StudentSettings(navController) }

}