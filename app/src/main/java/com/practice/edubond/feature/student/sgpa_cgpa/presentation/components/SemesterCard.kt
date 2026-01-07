package com.practice.edubond.feature.student.sgpa_cgpa.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.common.collect.Multimaps.index
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Semester
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Subject
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.UiSubject
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.viewModel.SgpaCgpaViewModel


@Composable
fun SemesterCard(
    semesterId: Int,
    semesterNumber: Int,
    subjects: List<UiSubject>,
    onAddSubject: () -> Unit,
    onUpdateSubject: (UiSubject,Int) -> Unit,
    onDeleteSubject: (Int) -> Unit
) {

    var expanded by remember { mutableStateOf(true) }



    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, Color(0x33FFFFFF)),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        ),
    ) {
        Box(
            modifier = Modifier
                .background(
                    ColorGradient.semesterCardGradient,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Semester ${semesterNumber}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                            else Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
            }
        }



        if (expanded) {
            Column(modifier = Modifier.padding(16.dp)) {

                subjects.forEachIndexed {index, subject ->
                    SubjectCard(
                        subject = subject,
                        onChange = { updated ->
                            onUpdateSubject(updated,index)
                        },
                        onDelete = { onDeleteSubject(index) }
                    )
                }


                    Spacer(modifier = Modifier.height(12.dp))
                    Buttons(
                        onClick = onAddSubject,
                        text = "+ Add New Subject",
                        backgroundColor = ColorGradient.buttonGradient
                    )


                    Spacer(modifier = Modifier.height(12.dp))
                    Buttons(
                        onClick = {},
                        text = "Calculate SGPA",
                        backgroundColor = ColorGradient.semesterCardGradient
                    )
                }


            }

        }
    }








