package com.practice.edubond.feature.auth.login

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.edubond.R

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val colorScheme = MaterialTheme.colorScheme

    val softBackgroundGradient = Brush.verticalGradient(
        colors = listOf(
            colorScheme.primary.copy(alpha = 0.08f),
            colorScheme.secondary.copy(alpha = 0.08f),
            colorScheme.tertiary.copy(alpha = 0.08f)
        )
    )

    val studentGradient = Brush.horizontalGradient(
        listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    )
    val teacherGradient = Brush.horizontalGradient(
        listOf( Color(0xFF009688), Color(0xFF3A8DFF),)
    )

    var selectedRole by remember { mutableStateOf<String?>(null) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = modifier.fillMaxSize()
         .background(softBackgroundGradient)
        .padding(top=36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.infominer_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(68.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Welcome Back!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Choose your role to continue",
            fontSize = 16.sp,
            color = colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(0.93f)
                .animateContentSize()
                .padding(vertical = 8.dp, horizontal = 2.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                Modifier.padding(vertical = 18.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoleSwitch(
                    selectedRole = selectedRole,
                    onRoleSelected = { selectedRole = it }
                )
                if (selectedRole != null) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        thickness = 1.dp,
                        color = colorScheme.outlineVariant
                    )
                        LazyColumn(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            item {
                                Text(
                                    text = "Email",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                OutlinedTextField(
                                    value = email,
                                    onValueChange = { email = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text(text = "Enter your email", color = colorScheme.onSurfaceVariant)
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.email_icon),
                                            contentDescription = "Email",
                                            modifier = Modifier.size(18.dp),
                                        )
                                    },
                                    singleLine = true,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFF2760FF),
                                        unfocusedBorderColor = Color(0xFF9E9E9E),
                                        cursorColor = Color(0xFF2760FF),
                                        focusedLabelColor = Color(0xFF2760FF)
                                    )
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = "Password",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorScheme.onSurface
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = password,
                                    onValueChange = { password = it },
                                    placeholder = { Text("Enter your password",color = colorScheme.onSurfaceVariant) },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.password_icon),
                                            contentDescription = "Email",
                                            modifier = Modifier.size(20.dp),
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFF2760FF),
                                        unfocusedBorderColor = Color(0xFF9E9E9E),
                                        cursorColor = Color(0xFF2760FF),
                                        focusedLabelColor = Color(0xFF2760FF)
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    singleLine = true,
                                    visualTransformation = PasswordVisualTransformation(),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    TextButton(
                                        onClick = { },
                                        contentPadding = PaddingValues(0.dp)) {
                                        Text(
                                            text = "Forgot Password?",
                                          //  color = colorScheme.primary
                                           color = Color(0xFF2760FF),
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                Button(
                                    onClick = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .width(20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                    ),
                                    contentPadding = PaddingValues(),
                                    shape = RoundedCornerShape(14.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
//                                    brush = if (authState.value == AuthState.Loading)
//                                        Brush.linearGradient(listOf(Color.LightGray, Color.Gray))
//                                    else
                                                studentGradient,
                                                shape = RoundedCornerShape(14.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Login",
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                ) {
                                    Divider(modifier = Modifier.weight(1f),  color = colorScheme.outlineVariant)
                                    Text(
                                        text = "Or continue with",
                                        modifier = Modifier.padding(horizontal = 12.dp),
                                        color = colorScheme.onSurfaceVariant,
                                        fontSize = 18.sp
                                    )
                                    Divider(modifier = Modifier.weight(1f),  color = colorScheme.outlineVariant)
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                OutlinedButton(
                                    onClick = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.google_icon),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        "Continue with Google",
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 16.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(20.dp))


                                Row(
                                    Modifier
                                        .padding(bottom = 16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "Don't have an account? ",
                                        color = colorScheme.onSurfaceVariant,
                                        fontSize = 14.sp
                                    )
                                    TextButton(
                                        onClick = {},
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            text = "Sign Up",
                                            color = colorScheme.primary,
                                            fontSize = 14.sp
                                        )
                                    }

                                }
                            }
                        }
                    }
                }

                            }


                        }
                    }



@Composable
fun RoleSwitch(selectedRole : String?,
               onRoleSelected: (String) -> Unit) {
    val shape = RoundedCornerShape(12.dp)
    val studentGradient = Brush.horizontalGradient(
        listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    )
    val teacherGradient = Brush.horizontalGradient(
        listOf( Color(0xFF009688), Color(0xFF3A8DFF),)
    )
    val defaultGradient = Brush.horizontalGradient(
        listOf( Color.Gray, Color.LightGray,)
    )

    Row(
        Modifier.height(70.dp)
            .padding(4.dp)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    if (selectedRole == "Student") studentGradient else defaultGradient,
                    shape
                )
                .clickable { onRoleSelected("Student") }

        ){
            Row(
                modifier = Modifier.padding(4.dp,0.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.graduation_cap),
                    contentDescription = "Student",
                    tint = if (selectedRole == "Student") Color.White else colorScheme.onSurface,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Student",
                    color = if (selectedRole == "Student") Color.White else colorScheme.onSurface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

            }
        }
        Spacer(modifier=Modifier.width(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    if (selectedRole == "Teacher") teacherGradient else defaultGradient,
                    shape
                )
                .clickable { onRoleSelected("Teacher") },
        ) {
            Row(
                Modifier.padding(horizontal = 4.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.person_icon),
                    contentDescription = null,
                    tint = if (selectedRole == "Teacher") Color.White else colorScheme.onSurface,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Teacher",
                    fontSize = 18.sp,
                    color = if (selectedRole == "Teacher") Color.White else colorScheme.onSurface,
                    fontWeight = FontWeight.Medium
                )



            }
        }

    }
}