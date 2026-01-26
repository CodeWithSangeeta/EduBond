package com.practice.edubond.feature.student.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun StudentHomeScreen(navController: NavHostController) {
    Text("I am a student",fontSize = 80.sp)
}