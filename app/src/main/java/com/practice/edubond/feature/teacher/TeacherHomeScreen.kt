package com.practice.edubond.feature.teacher

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TeacherHomeScreen(navController: NavHostController) {
    Text("I am a Teacher",fontSize = 40.sp)
}