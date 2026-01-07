package com.practice.edubond.feature.student.sgpa_cgpa.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Subject
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectCard(
    subject: SubjectEntity,
    onChange: (SubjectEntity) -> Unit,
    onDelete: () -> Unit
) {
    var name by remember(subject.subjectId) {
        mutableStateOf(subject.name)
    }

    var credits by remember(subject.subjectId) {
        mutableStateOf(subject.credits.toString())
    }

    var grade by remember(subject.subjectId) {
        mutableStateOf(subject.grade)
    }
    var isGradeExpanded by remember { mutableStateOf(false) }
    val grades = listOf("O", "A+", "A", "B+", "B", "C", "P", "F")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF7FAFC) // light card background
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            /* ---------- SUBJECT NAME + DELETE ---------- */

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        onChange(subject.copy(name = it))
                    },
                    placeholder = {
                        Text(
                            "Subject Name",
                            color = Color(0xFF9CA3AF)
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE5E7EB),
                        unfocusedBorderColor = Color(0xFFE5E7EB),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFFFFE4E4))
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color(0xFFEF4444)
                    )
                }
            }

            /* ---------- CREDITS + GRADE ---------- */

            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                // Credits
                OutlinedTextField(
                    value = credits,
                    onValueChange = {
                        if (it.all { c -> c.isDigit() }) {
                            credits = it
                            onChange(subject.copy(credits = it.toIntOrNull() ?: 0))
                        }
                    },
                    placeholder = {
                        Text(
                            "Credits",
                            color = Color(0xFF9CA3AF)
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE5E7EB),
                        unfocusedBorderColor = Color(0xFFE5E7EB),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                // Grade Dropdown
                ExposedDropdownMenuBox(
                    expanded = isGradeExpanded,
                    onExpandedChange = { isGradeExpanded = !isGradeExpanded },
                    modifier = Modifier.weight(1f)
                ) {

                    OutlinedTextField(
                        value = grade,
                        onValueChange = {},
                        readOnly = true,
                        placeholder = {
                            Text(
                                "Grade",
                                color = Color(0xFF111827),
                                fontWeight = FontWeight.Medium
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE5E7EB),
                            unfocusedBorderColor = Color(0xFFE5E7EB),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = isGradeExpanded,
                        onDismissRequest = { isGradeExpanded = false }
                    ) {
                        grades.forEach { grade ->
                            DropdownMenuItem(
                                text = { Text(grade) },
                                onClick = {
                                    onChange(subject.copy(grade = grade))
                                    isGradeExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
