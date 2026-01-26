package com.practice.edubond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.edubond.app_navigation.MainNavGraph
import com.practice.edubond.feature.auth.login.LoginScreen
import com.practice.edubond.feature.auth.state.AuthViewModel

import com.practice.edubond.feature.student.sgpa_cgpa.presentation.screen.SgpaCgpaScreen
import com.practice.edubond.ui.theme.EduBondTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authViewModel: AuthViewModel = hiltViewModel()
            EduBondTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
               MainNavGraph(authViewModel=authViewModel)
                }
            }
        }
    }
}


