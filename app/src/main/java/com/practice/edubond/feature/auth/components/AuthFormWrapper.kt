package com.practice.edubond.feature.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthFormWrapper(selectedRole: String?,
                    onRoleSelected: (String) -> Unit,
                    content : @Composable () -> Unit) {
    RoleSwitch(
        selectedRole = selectedRole,
        onRoleSelected = onRoleSelected
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
                content()
            }
        }
    }
}
