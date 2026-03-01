package com.practice.edubond.feature.student.screens.components

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object StudentGradientColors{
    val background = Brush.Companion.verticalGradient(
        listOf(
            Color(0xFF6A11CB).copy(alpha = 0.08f),
            Color(0xFF2575FC).copy(alpha = 0.08f),
            Color(0xFF009688).copy(alpha = 0.08f)
        )
    )
    val default = Brush.Companion.horizontalGradient(
        listOf( Color.Companion.Gray, Color.Companion.LightGray,)
    )

    val student = Brush.Companion.horizontalGradient(
        listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    )
    val teacher = Brush.Companion.horizontalGradient(
        listOf(
            Color(0xFF3A8DFF),
            Color(0xFF009688),
        )
    )
}