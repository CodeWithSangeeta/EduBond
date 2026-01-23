package com.practice.edubond.feature.auth.login

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.practice.edubond.app_navigation.MainRoutes
import com.practice.edubond.feature.auth.state.AuthViewModel
import com.practice.edubond.feature.auth.components.AuthCard
import com.practice.edubond.feature.auth.components.AuthFormWrapper
import com.practice.edubond.feature.auth.components.AuthText
import com.practice.edubond.feature.auth.components.AuthTextField
import com.practice.edubond.feature.auth.components.GoogleButton
import com.practice.edubond.feature.auth.components.GradientButton
import com.practice.edubond.feature.auth.components.LogoHeader
import com.practice.edubond.feature.auth.components.PasswordTextField
import com.practice.edubond.feature.auth.components.AuthGradient
import com.practice.edubond.feature.auth.components.AuthSwitchText
import com.practice.edubond.feature.auth.components.SocialDivider
import com.practice.edubond.feature.auth.navigation.AuthRoutes
import com.practice.edubond.feature.auth.state.AuthFormViewModel



@Composable
fun LoginScreen(
    navController : NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val parentEntry = remember(navController) {
        navController.getBackStackEntry(MainRoutes.AUTH)
    }
    val authViewModel : AuthViewModel = hiltViewModel(parentEntry)
    val authFormViewModel: AuthFormViewModel = hiltViewModel(parentEntry)
    val sharedRole by authFormViewModel.selectedRole.collectAsState()
    val state by viewModel.state.collectAsState()
    val loginSuccess by viewModel.loginSuccess.collectAsState()


    LaunchedEffect(loginSuccess) {
        loginSuccess?.let { (userId, role) ->
            authViewModel.onLoginSuccess(userId, role)
            authFormViewModel.clear() // optional, clean up
        }
    }

    LaunchedEffect(sharedRole) {
        viewModel.onEvent(LoginEvent.RoleUpdated(sharedRole))
    }


    Column(
        modifier = Modifier.fillMaxSize()
         .background(AuthGradient.background)
        .padding(top=36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        LogoHeader(title = "Welcome Back", subtitle = "Choose your role to continue")

        AuthCard {
                    AuthFormWrapper(
                        selectedRole = sharedRole,
                        onRoleSelected = { role ->
                            authFormViewModel.updateRole(role)
                            viewModel.onEvent(LoginEvent.RoleUpdated(role))
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


                        state.error?.let {
                            Text(
                                text = it,
                                color = Color.Red,
                                fontSize = 13.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 6.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                        when {
                            state.isLoading -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(strokeWidth = 2.dp)
                                }
                            }

                            else -> {
                                GradientButton(
                                    text = "Login",
                                    selectedRole = sharedRole,
                                    onClick = {
                                        viewModel.onEvent(LoginEvent.LoginClicked)
                                    }
                                )
                            }
                        }

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




