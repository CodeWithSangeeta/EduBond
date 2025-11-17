package com.practice.edubond.feature.auth.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AuthTextField(
    value : String,
    onChange : (String) -> Unit,
    label : String,
    leadingIcon : ImageVector,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange ,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = label, color = colorScheme.onSurfaceVariant)
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(22.dp),
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
}