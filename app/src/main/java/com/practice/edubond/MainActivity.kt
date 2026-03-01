package com.practice.edubond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.edubond.app_navigation.MainNavGraph
import com.practice.edubond.feature.auth.state.AuthViewModel
import com.practice.edubond.feature.student.screens.StudentDashboard

import com.practice.edubond.ui.theme.EduBondTheme
import com.practice.edubond.ui.theme.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authViewModel: AuthViewModel = hiltViewModel()
            val themeViewModel: ThemeViewModel = hiltViewModel()
            EduBondTheme(  darkTheme = themeViewModel.isDarkMode.collectAsState().value) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
               MainNavGraph(
                   authViewModel=authViewModel,
                   themeViewModel=themeViewModel
               )
                }
            }

           //StudentDashboard(navController, themeViewModel) { themeViewModel.toggleTheme() }
        }
    }
}


