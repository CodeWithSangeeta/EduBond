package com.practice.edubond.feature.auth.login

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.practice.edubond.R
import com.practice.edubond.app_navigation.MainRoutes
import com.practice.edubond.feature.auth.AuthViewModel
import com.practice.edubond.feature.auth.components.AuthCard
import com.practice.edubond.feature.auth.components.AuthFormWrapper
import com.practice.edubond.feature.auth.components.AuthText
import com.practice.edubond.feature.auth.components.AuthTextField
import com.practice.edubond.feature.auth.components.GoogleButton
import com.practice.edubond.feature.auth.components.GradientButton
import com.practice.edubond.feature.auth.components.LogoHeader
import com.practice.edubond.feature.auth.components.PasswordTextField
import com.practice.edubond.feature.auth.components.RoleSwitch
import com.practice.edubond.feature.auth.components.AuthGradient
import com.practice.edubond.feature.auth.components.AuthSwitchText
import com.practice.edubond.feature.auth.components.SocialDivider
import com.practice.edubond.feature.auth.navigation.AuthRoutes
import com.practice.edubond.feature.auth.signup.SignupEvent


@Composable
fun LoginScreen(
    navController : NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val parentEntry = remember(navController) {
        navController.getBackStackEntry(MainRoutes.AUTH)
    }
    val authViewModel : AuthViewModel = hiltViewModel(parentEntry)
    val role by authViewModel.selectedRole.collectAsState()
    val state by viewModel.state.collectAsState()

//    LaunchedEffect(role) {
//        viewModel.onEvent(LoginEvent.RoleUpdated(role))
//    }


    Column(
        modifier = Modifier.fillMaxSize()
         .background(AuthGradient.background)
        .padding(top=36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        // Navigate when authenticated
        LaunchedEffect(state.isAuthenticated) {
            if (state.isAuthenticated) {
                navController.navigate(MainRoutes.STUDENT_HOME) {
                    popUpTo(MainRoutes.AUTH) { inclusive = true }
                }
            }
        }

// Show loading
        if (state.isLoading) {
            CircularProgressIndicator()
        }

// Show error
        state.error?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(12.dp)
            )
        }


        LogoHeader(title = "Welcome Back", subtitle = "Choose your role to continue")

        AuthCard {
                    AuthFormWrapper(
                        selectedRole = role,
                        onRoleSelected = {
                            authViewModel.selectRole(it)
                        }
                    ) {
                                AuthText("Email")
                                AuthTextField(
                                    value= state.email,
                                    onChange = {viewModel.onEvent(LoginEvent.EmailChanged(it))},
                                    label = "Enter your email",
                                    leadingIcon = Icons.Default.Email
                                )

                               AuthText("Password")
                                PasswordTextField(
                                    text= state.password,
                                    onChange = {viewModel.onEvent(LoginEvent.PasswordChanged(it))},
                                    label = "Enter your password",
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    TextButton(
                                        onClick = { },
                                        contentPadding = PaddingValues(0.dp)) {
                                        Text(
                                            text = "Forgot Password?",
                                           color = Color(0xFF2760FF),
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(20.dp))


                                GradientButton(
                                    text = "Login",
                                    selectedRole = role,
                                    //enabled = selectedRole != null,
                                    onClick = { viewModel.onEvent(LoginEvent.LoginClicked)}
                                )
                                SocialDivider("Or Continue with")
                                GoogleButton{}
                                AuthSwitchText("Don't have an account?","Sign Up", onClick = {navController.navigate(AuthRoutes.SIGNUP){
                                    launchSingleTop = true
                                } })

                                Spacer(modifier = Modifier.height(20.dp))
                                }
                            }
                        }
                    }




