package com.practice.edubond.feature.student.presentation.sgpa_cgpa.screen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.components.ColorGradient
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.components.HeaderSection
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.components.SemesterCard
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Semester
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.UiSubject
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SemesterEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.viewModel.SgpaCgpaViewModel
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.viewModel.SgpaCgpaViewModelFactory
import kotlin.text.get

@Composable
fun SgpaCgpaScreen() {

    val context = LocalContext.current

    val viewModel: SgpaCgpaViewModel = viewModel(
        factory = SgpaCgpaViewModelFactory(context)
    )

    // ✅ Correctly collected
    val semesters by viewModel.semesters.collectAsState()




    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HeaderSection()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            items(items = semesters,
                key = {it.semesterId}
                ) { semester : SemesterEntity->

                LaunchedEffect(semester.semesterId) {
                    viewModel.ensureUiSubjectForSemester(semester.semesterId)
                }

                val dbSubjects by viewModel
                    .getSubjectsForSemester(semester.semesterId)
                    .collectAsState(initial = emptyList())


                val uiSubjectsForSemester =
                    viewModel.uiSubjects.collectAsState().value[semester.semesterId]
                        ?: emptyList()

                val subjectsToShow =
                    if (dbSubjects.isEmpty()) uiSubjectsForSemester
                    else dbSubjects.map {
                        UiSubject(
                            id = it.subjectId,
                            name = it.name,
                            credits = it.credits,
                            grade = it.grade
                        )
                    }



                SemesterCard(
                    semesterId = semester.semesterId,
                    semesterNumber = semester.semesterNumber,
                    subjects = subjectsToShow,
                    onAddSubject = {
                        viewModel.addUiSubject(semester.semesterId)
                    },
                    onUpdateSubject = { subject, index ->
                        viewModel.updateUiSubject(
                            semester.semesterId,
                            index,
                            subject
                        )
                    },
                    onDeleteSubject = { index ->
                        viewModel.deleteUiSubject(
                            semester.semesterId,
                            index
                        )
                    }
                )


            }

            item {
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
                Spacer(modifier = Modifier.height(42.dp))

            }
        }
    }
}

