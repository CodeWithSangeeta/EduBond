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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.practice.edubond.R
import com.practice.edubond.app_navigation.MainRoutes
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


@Composable
fun LoginScreen(navController: NavController) {

    val viewModel : LoginViewModel = viewModel()
    val loginState by viewModel.loginState.observeAsState()

    var selectedRole by remember { mutableStateOf<String?>(null) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
//        modifier = modifier.fillMaxSize()
//         .background(AuthGradient.background)
//        .padding(top=36.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // 🎯 React to LoginState
        // ---------------------------
        when (loginState) {

            is LoginState.Loading -> {
                CircularProgressIndicator()
            }

            is LoginState.Error -> {
                Text(
                    text = (loginState as LoginState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(12.dp)
                )
            }

            is LoginState.Authenticated -> {
                LaunchedEffect(Unit) {
                    navController.navigate(MainRoutes.STUDENT_HOME) {
                        popUpTo(MainRoutes.AUTH) { inclusive = true }
                    }
                }
            }

            else -> {}
        }


       LogoHeader(title = "Welcome Back", subtitle = "Choose your role to continue")

        AuthCard {
                    AuthFormWrapper(
                        selectedRole = selectedRole,
                        onRoleSelected = { selectedRole = it }
                    ) {
                                AuthText("Email")
                                AuthTextField(
                                    value= email,
                                    onChange = {email = it},
                                    label = "Enter your email",
                                    leadingIcon = Icons.Default.Email
                                )

                               AuthText("Password")
                                PasswordTextField(
                                    text= password,
                                    onChange = {password = it},
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
                                    selectedRole = selectedRole,
                                    onClick = {viewModel.login(email,password)}
                                )
                                SocialDivider("Or Continue with")
                                GoogleButton{}
                                AuthSwitchText("Don't have an account?","Sign Up", onClick = {navController.navigate(
                                    AuthRoutes.SIGNUP)})

                                }
                            }
                        }
                    }




