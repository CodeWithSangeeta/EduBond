package com.practice.edubond.feature.student.sgpa_cgpa.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.components.ColorGradient
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.components.HeaderSection
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.components.SemesterCard
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.viewModel.SgpaCgpaViewModel

@Composable
fun SgpaCgpaScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        val viewModel: SgpaCgpaViewModel = viewModel()
        val semesters = viewModel.semesters
        HeaderSection()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(semesters) { semester ->
                SemesterCard(
                    semester = semester,
                    onAddSubject = {
                        viewModel.addSubject(semester.id)
                    },
                    onUpdateSubject = { index, updated ->
                        viewModel.updateSubject(semester.id, index, updated)
                    }
                )


                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(55.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(brush = ColorGradient.topAppGradient)
                        .clickable { viewModel.addSemester() }
                ) {
                    Text(
                        text = "+ Add New Semester",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}

