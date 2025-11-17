package com.practice.edubond.feature.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.edubond.R
import com.practice.edubond.feature.auth.components.AuthGradient

@Composable
fun RoleSwitch(selectedRole : String?,
               onRoleSelected: (String) -> Unit) {
    val shape = RoundedCornerShape(12.dp)

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
                    if (selectedRole == "Student") AuthGradient.student else AuthGradient.default,
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
                    if (selectedRole == "Teacher") AuthGradient.teacher else AuthGradient.default,
                    shape
                )
                .clickable { onRoleSelected("Teacher") },
        ) {
            Row(
                Modifier.padding(horizontal = 4.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = if (selectedRole == "Teacher") Color.White else colorScheme.onSurface,
                    modifier = Modifier.size(32.dp)
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