package com.practice.edubond.feature.student.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.practice.edubond.feature.student.screens.StudentHomeScreen

fun NavGraphBuilder.studentNavGraph(
    navController: NavHostController
)
{
    composable(route = StudentRoutes.HOME) {
        StudentHomeScreen(navController)
    }
    composable(route = StudentRoutes.PROFILE) {

    }


}