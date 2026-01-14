package com.practice.edubond.feature.teacher.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.practice.edubond.feature.teacher.screens.TeacherHomeScreen

fun NavGraphBuilder.teacherNavGraph(
    navController: NavHostController
)
{
    composable(route = TeacherRoutes.HOME) {
      TeacherHomeScreen(navController)
    }
    composable(route = TeacherRoutes.PROFILE) {

    }


}