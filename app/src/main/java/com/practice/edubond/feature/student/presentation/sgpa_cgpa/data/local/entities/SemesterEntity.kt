package com.practice.edubond.feature.student.presentation.sgpa_cgpa.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "semesters")
data class SemesterEntity(

    @PrimaryKey(autoGenerate = true)
    val semesterId: Int = 0,

    val semesterNumber: Int
)
