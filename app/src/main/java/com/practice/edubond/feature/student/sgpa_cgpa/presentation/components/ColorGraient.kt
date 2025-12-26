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
    val subjectCardGradient = Brush.linearGradient(
        listOf(
            Color(0xFFB3E5FC).copy(alpha = 0.45f), // light blue
            Color(0xFFD1C4E9).copy(alpha = 0.45f)
        )
    )


    val buttonGradient = Brush.linearGradient(
        listOf(
            Color(0xFFAAC5C9),
            Color(0xFFB79CC7)
        )
    )


}