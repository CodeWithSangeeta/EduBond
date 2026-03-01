package com.practice.edubond.feature.student.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class StudentBottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    HOME(StudentRoutes.STUDENT_DASHBOARD, Icons.Default.Home, "Home"),
    BADGES("${StudentRoutes.STUDENT_DASHBOARD}/badges", Icons.Default.EmojiEvents, "Badges"),
    PROGRESS("${StudentRoutes.STUDENT_DASHBOARD}/progress", Icons.Default.Leaderboard, "Progress"),
    SETTINGS("${StudentRoutes.STUDENT_DASHBOARD}/settings", Icons.Default.Settings, "Settings");

    companion object {
        fun fromRoute(route: String?): StudentBottomNavItem =
            entries.find { it.route == route } ?: HOME
    }
}