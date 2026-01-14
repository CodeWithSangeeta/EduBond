package com.practice.edubond.app_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.practice.edubond.feature.auth.navigation.AuthRoutes
import com.practice.edubond.feature.auth.navigation.authNavGraph
import com.practice.edubond.feature.student.navigation.StudentRoutes
import com.practice.edubond.feature.student.navigation.studentNavGraph
import com.practice.edubond.feature.teacher.navigation.TeacherRoutes
import com.practice.edubond.feature.teacher.navigation.teacherNavGraph

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()

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
}