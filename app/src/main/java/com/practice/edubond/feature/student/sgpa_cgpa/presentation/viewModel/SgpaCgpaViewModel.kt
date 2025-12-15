package com.practice.edubond.feature.student.sgpa_cgpa.presentation.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Semester
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Subject

class SgpaCgpaViewModel : ViewModel() {

    var semesters = mutableStateListOf<Semester>()
        private set

    init {
        addSemester()
    }

    fun addSemester() {
        val newSemester = Semester(
            id = semesters.size + 1
        )
        semesters.add(newSemester)
    }

    fun addSubject(semesterId: Int) {
        val semester = semesters.find { it.id == semesterId }
        semester?.subjects?.add(Subject())
    }

    fun updateSubject(
        semesterId: Int,
        index: Int,
        updated: Subject
    ) {
        val semester = semesters.find { it.id == semesterId }
        semester?.subjects?.set(index, updated)
    }

    fun  onDeleteSubject(semesterId: Int, index: Int) {
        val semester = semesters.find { it.id == semesterId }
        semester?.subjects?.removeAt(index)
    }

}
