package com.practice.edubond.feature.auth.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.edubond.R

@Composable
fun LogoHeader(title: String,subtitle : String) {
    Icon(
        painter = painterResource(id = R.drawable.infominer_logo),
        contentDescription = "App Logo",
        modifier = Modifier.size(68.dp),
        tint = Color.Unspecified
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = title,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = colorScheme.onSurface,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = subtitle,
        fontSize = 16.sp,
        color = colorScheme.onSurfaceVariant
    )
    Spacer(modifier = Modifier.height(24.dp))
}