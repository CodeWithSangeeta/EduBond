package com.practice.edubond.feature.student

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun StudentHomeScreen(navController: NavHostController) {
    Text("I am a student",fontSize = 40.sp)
}