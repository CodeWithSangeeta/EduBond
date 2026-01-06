package com.practice.edubond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.practice.edubond.feature.auth.login.LoginScreen
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.screen.SgpaCgpaScreen
import com.practice.edubond.ui.theme.EduBondTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   val authViewModel : LoginViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            EduBondTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   // LoginScreen()
                 //   SignupScreen()

                    SgpaCgpaScreen()
                }
            }
        }
    }
}


