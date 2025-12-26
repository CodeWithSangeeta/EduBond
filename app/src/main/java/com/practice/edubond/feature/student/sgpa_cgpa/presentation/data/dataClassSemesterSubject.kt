package com.practice.edubond.feature.student.sgpa_cgpa.presentation.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Semester(
    val id : Int,
    val subjects : SnapshotStateList<Subject> = mutableStateListOf()

)

data class Subject(
    var name : String = "",
    var credits : Int = 0,
    var grade : String = ""
)
