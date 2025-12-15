package com.practice.edubond.feature.student.sgpa_cgpa.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectCard(
    subject: Subject,
    onChange: (Subject) -> Unit,
    onDelete: () -> Unit
) {
    var subjectName by remember { mutableStateOf(subject.name) }
    var credits by remember { mutableStateOf(subject.credits.toString()) }
    var selectedGrade by remember { mutableStateOf(subject.grade) }
    var isGradeExpanded by remember { mutableStateOf(false) }
    val grades = listOf("O", "A+", "A", "B+", "B", "C", "P", "F")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Subject Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = subjectName,
                    onValueChange = {
                        subjectName = it
                        onChange(Subject(name = subjectName,
                            credits=credits.toIntOrNull() ?: 0,
                            grade = selectedGrade))
                                    },
                    placeholder = { Text("Subject Name / Subject Code") },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                IconButton(onClick = {onDelete()}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }

            // Credits + Grade Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // Credits TextField with Stepper
                OutlinedTextField(
                    value = credits,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            credits = it
                            onChange(Subject(subjectName, credits.toIntOrNull() ?: 0, selectedGrade))
                        }
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    placeholder = { Text("Credits") },
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {

                            // Increase Arrow
                            IconButton(
                                onClick = {
                                    val num = credits.toIntOrNull() ?: 0
                                    credits = (num + 1).toString()
                                },
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(18.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = "Increase"
                                )
                            }

                            // Decrease Arrow
                            IconButton(
                                onClick = {
                                    val num = credits.toIntOrNull() ?: 0
                                    if (num > 0) {
                                        credits = (num - 1).toString()
                                    }
                                },
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(18.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Decrease"
                                )
                            }
                        }
                    }
                )



                // Grade Dropdown
                ExposedDropdownMenuBox(
                    expanded = isGradeExpanded,
                    onExpandedChange = { isGradeExpanded = !isGradeExpanded },
                    modifier = Modifier.weight(1f)
                ) {

                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        value = selectedGrade,
                        onValueChange = {
                            selectedGrade = it
                            onChange(Subject(subjectName, credits.toIntOrNull() ?: 0, selectedGrade))
                        },
                        readOnly = true,
                        singleLine = true,
                        placeholder = { Text("Grade") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        },
                        shape = RoundedCornerShape(16.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = isGradeExpanded,
                        onDismissRequest = { isGradeExpanded = false }
                    ) {
                        grades.forEach { grade ->
                            DropdownMenuItem(
                                text = { Text(grade) },
                                onClick = {
                                    selectedGrade = grade
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



