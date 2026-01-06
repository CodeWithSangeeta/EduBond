package com.practice.edubond.feature.auth.signup

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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.runtime.getValue
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
import com.practice.edubond.R
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

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun SignupScreen(modifier: Modifier = Modifier) {
    var selectedRole by remember { mutableStateOf<String?>(null) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm_password by remember { mutableStateOf("") }
    var checked by remember {mutableStateOf(false)}


    Column(
        modifier = modifier.fillMaxSize()
            .background(AuthGradient.background)
            .padding(top=36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LogoHeader(title="Create Your Account", subtitle = "Join EduBond to start your learning journey")

        Text(
            text = "Select Your Role",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onSurface,
            modifier = Modifier.align(Alignment.Start)
                .padding(start=36.dp)
        )
       AuthCard{
                    AuthFormWrapper(
                        selectedRole = selectedRole,
                        onRoleSelected = { selectedRole = it }
                    ) {
                            AuthText("Full Name")
                            AuthTextField(
                                value= name,
                                onChange = {name = it},
                                label = "Enter your full name",
                                leadingIcon = Icons.Default.Person
                            )

                            AuthText("Email Address")
                            AuthTextField(
                                value= email,
                                onChange = {email = it},
                                label = "your.email@example.com",
                                leadingIcon = Icons.Default.Email
                            )

                           AuthText("Phone Number" )
                            AuthTextField(
                                value= phoneNo,
                                onChange = {phoneNo = it},
                                label = "+91 1234567890",
                                leadingIcon = Icons.Default.Phone
                            )

                            AuthText("Password")
                            PasswordTextField(
                                text = password,
                                onChange = { password = it },
                                label = "Create a password",
                            )

                           AuthText("Confirm Password")
                            PasswordTextField(
                                text = confirm_password,
                                onChange = { confirm_password = it },
                                label = "Re-enter your password",
                            )


                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center

                                    ) {
                                        Checkbox(
                                            checked = checked,
                                            onCheckedChange = { checked = it },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = Color(0xFF2760FF),
                                                uncheckedColor = Color.Gray,
                                                checkmarkColor = Color.White
                                            ),
                                            modifier = Modifier.size(20.dp),
                                        )
                                        Spacer(modifier.size(8.dp))

                                        Text(
                                            text = "I agree to the ",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        TextButton(
                                            onClick = { },
                                            contentPadding = PaddingValues(0.dp)
                                        ) {
                                            Text(
                                                text = "Terms of Service ",
                                                color = Color(0xFF2760FF),
                                                fontSize = 12.sp
                                            )
                                        }
                                        Text(
                                            text = "and ",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )

                                        TextButton(
                                            onClick = { },
                                            contentPadding = PaddingValues(0.dp)
                                        ) {
                                            Text(
                                                text = "Privacy Policy ",
                                                color = Color(0xFF2760FF),
                                                fontSize = 12.sp
                                            )
                                        }

                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            GradientButton(
                                text = "Sign Up",
                                selectedRole = selectedRole,
                                onClick = {}
                            )

                        SocialDivider("Or sign up with")
                        GoogleButton{}
                        AuthSwitchText("Already have an account? ","Login", onClick = {})

                        }
                    }
                }
    }




