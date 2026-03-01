package com.practice.edubond.feature.student.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.practice.edubond.feature.components.StudentBottomNav
import com.practice.edubond.feature.student.navigation.StudentRoutes
import com.practice.edubond.feature.student.screens.components.DashboardCard
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.components.ColorGradient
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboard() {
    var currentTime by remember { mutableStateOf("") }
    var studyStreak by remember { mutableStateOf(7) }
    val isDarkMode by remember { mutableStateOf(false) }

    // Update time every second
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = java.text.SimpleDateFormat("h:mm a", java.util.Locale.getDefault())
                .format(java.util.Date())
            delay(1000)
        }
    }

    Scaffold(
        bottomBar = {
            StudentBottomNav(navController = navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
//            .background(
//                if (isDarkMode) Color(0xFF0F0F23) else Color(0xFFF8FAFC)
//            )
        ) {
            // Top App Bar
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Hello, Sangeeta ✨", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Toggle theme */ }) {
                        Icon(Icons.Default.DarkMode, contentDescription = "Theme")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.background(
                    Brush.Companion.horizontalGradient(
                        listOf(Color.Companion.Gray, Color.Companion.LightGray,)
                    )
                )

            )

            // Content
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                item {
                    // Greeting & Streak
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Ready to study? ✨",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            // Study Streak
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFFF6B6B))
                                ) {
                                    Icon(
                                        Icons.Default.LocalFireDepartment,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "${studyStreak} Days Streak",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1F2937)
                                    )
                                    Text(
                                        text = "Keep it up! 🔥",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            // Quote of the Day
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9))
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = Color(0xFFF59E0B),
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Today's Motivation",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "\"Success is the sum of small efforts, repeated daily.\"",
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center,
                                        color = Color(0xFF6B7280)
                                    )
                                }
                            }
                        }
                    }
                }

                // Quick Access Cards Row 1
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        item {
                            DashboardCard(
                                title = "SGPA/CGPA",
                                subtitle = "Calculate grades",
                                // icon = Icons.Default.Calculate,
                                gradient = ColorGradient.topAppGradient,
                                onClick = {
                                    //     navController.navigate("${StudentRoutes.STUDENT_DASHBOARD}/sgpa")
                                }
                            )
                        }
                        item {
                            DashboardCard(
                                title = "Previous Papers",
                                subtitle = "Upload & download",
                                gradient = Brush.Companion.horizontalGradient(
                                    listOf(Color.Companion.Gray, Color.Companion.LightGray,)
                                ),
                                onClick = { }
                            )
                        }
                    }
                }

                // Quick Access Cards Row 2
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        item {
                            DashboardCard(
                                title = "Calendar",
                                subtitle = "Plan your schedule",
                                // icon = Icons.Default.Event,
                                gradient = Brush.Companion.horizontalGradient(
                                    listOf(Color.Companion.Gray, Color.Companion.LightGray,)
                                ),
                                onClick = { }
                            )
                        }
                        item {
                            DashboardCard(
                                title = "Timer",
                                subtitle = "Pomodoro focus",
                                //  icon = Icons.Default.Timer,
                                gradient = Brush.Companion.horizontalGradient(
                                    listOf(Color.Companion.Gray, Color.Companion.LightGray,)
                                ),
                                badgeCount = 3,
                                onClick = { }
                            )
                        }
                    }
                }


//            // Bottom Navigation Preview (for design consistency)
//            item {
//                Spacer(modifier = Modifier.height(80.dp))
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    BottomNavigationItem(
//                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
//                        label = { Text("Home") },
//                        selected = true,
//                        onClick = { }
//                    )
//                    BottomNavigationItem(
//                        icon = { Icon(Icons.Default.EmojiEvents, contentDescription = null) },
//                        label = { Text("Badges") },
//                        selected = false,
//                        onClick = { }
//                    )
//                    BottomNavigationItem(
//                        icon = { Icon(Icons.Default.Leaderboard, contentDescription = null) },
//                        label = { Text("Progress") },
//                        selected = false,
//                        onClick = { }
//                    )
//                    BottomNavigationItem(
//                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
//                        label = { Text("Settings") },
//                        selected = false,
//                        onClick = { }
//                    )
//                }
//            }


            }
        }
    }
}
