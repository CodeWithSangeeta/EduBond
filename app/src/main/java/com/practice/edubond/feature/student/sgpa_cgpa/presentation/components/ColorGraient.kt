package com.practice.edubond.feature.student.sgpa_cgpa.presentation.components

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object ColorGradient{
    val topAppGradient = Brush.linearGradient(
        listOf(
            Color(0xFF3CC6CD),
            Color(0xFF833CC1),
            Color(0xFFB96CD2)
        )
    )


    val semesterCardGradient = Brush.horizontalGradient(
        listOf(
            Color(0xFF2193b0),
            Color(0xFF690EC6)
        )
    )
    val lightGradient = Brush.horizontalGradient(
        listOf(
            Color(0xFF99CECD),
            Color(0xFFA09ABA)
        )
    )


}