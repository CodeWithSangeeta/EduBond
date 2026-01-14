package com.practice.edubond.feature.auth.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.practice.edubond.feature.auth.components.AuthGradient

@Composable
fun GradientButton(
    text: String,
    selectedRole : String?,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if(selectedRole == "Student") AuthGradient.student
                else if(selectedRole == "Teacher") AuthGradient.teacher
                else AuthGradient.default,
                    RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
                Text(
                    text = text,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )

        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}
