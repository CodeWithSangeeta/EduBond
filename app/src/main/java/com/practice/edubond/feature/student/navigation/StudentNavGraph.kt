package com.practice.edubond.feature.student.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.practice.edubond.feature.auth.state.AuthViewModel
import com.practice.edubond.feature.student.screens.StudentDashboard
import com.practice.edubond.ui.theme.ThemeViewModel

fun NavGraphBuilder.studentNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    themeViewModel: ThemeViewModel   // ✅ Fix parameter
) {
    composable(StudentRoutes.STUDENT_DASHBOARD) {
        StudentDashboard(
            navController = navController,
            themeViewModel = themeViewModel  // ✅ Pass it correctly
        )
    }
}