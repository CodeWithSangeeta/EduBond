package com.practice.edubond.feature.student.sgpa_cgpa.presentation.data

data class Semester(
    val id : Int,
    val subjects : MutableList<Subject> = mutableListOf()

)

data class Subject(
    var name : String = "",
    var credits : Int = 0,
    var grade : String = ""
)
