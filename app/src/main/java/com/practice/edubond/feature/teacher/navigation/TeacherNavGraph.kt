package com.practice.edubond.feature.teacher.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.practice.edubond.feature.teacher.screens.TeacherDashboard

fun NavGraphBuilder.teacherNavGraph(
    navController: NavHostController
)
{
    composable(route = TeacherRoutes.TEACHER_DASHBOARD) {
      TeacherDashboard(navController)
    }
    composable(route = TeacherRoutes.TEACHER_PROFILE) {

    }


}