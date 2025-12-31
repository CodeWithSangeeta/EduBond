package com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "subjects",
    foreignKeys = [
        ForeignKey(
            entity = SemesterEntity::class,
            parentColumns = ["semesterId"],
            childColumns = ["semesterOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("semesterOwnerId")]
)
data class SubjectEntity(

    @PrimaryKey(autoGenerate = true)
    val subjectId: Int = 0,

    val semesterOwnerId: Int,

    val name: String,
    val credits: Int,
    val grade: String
)
