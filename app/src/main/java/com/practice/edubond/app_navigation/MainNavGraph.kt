package com.practice.edubond.app_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.practice.edubond.feature.auth.navigation.AuthRoutes
import com.practice.edubond.feature.auth.navigation.authNavGraph
import com.practice.edubond.feature.auth.signup.SignupScreen
import com.practice.edubond.feature.teacher.TeacherHomeScreen

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



//        // 🎓 Student graph (future)
//        navigation(
//            startDestination = MainRoutes.STUDENT_HOME,
//            route = MainRoutes.STUDENT_HOME
//        ) {
//            composable(MainRoutes.STUDENT_HOME) {
//                StudentHomeScreen(navController)
//            }
//        }
//
//        // 👩‍🏫 Teacher graph (future)
//        navigation(
//            startDestination = MainRoutes.TEACHER_HOME,
//            route = MainRoutes.TEACHER_HOME
//        ) {
//            composable(MainRoutes.TEACHER_HOME) {
//                TeacherHomeScreen(navController)
//            }
//        }





    })
}