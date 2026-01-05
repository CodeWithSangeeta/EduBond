package com.practice.edubond.feature.student.sgpa_cgpa.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.SgpaCgpaRepository
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SemesterEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SgpaCgpaViewModel(
    private val repository: SgpaCgpaRepository
) : ViewModel() {

    /* ---------- SEMESTERS ---------- */

    val semesters: StateFlow<List<SemesterEntity>> =
        repository.getAllSemesters()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )


    init {
        viewModelScope.launch {
            if (semesters.value.isEmpty()) {
                repository.insertSemester(
                    SemesterEntity(semesterNumber = 1)
                )
            }
        }
    }

    /* ---------- ACTIONS ---------- */

    fun addSemester() {
        viewModelScope.launch {
            val semesterNumber = semesters.value.size + 1
            repository.insertSemester(
                SemesterEntity(semesterNumber = semesterNumber)
            )
        }
    }

//    fun deleteSemester() {
//        viewModelScope.launch {
//           repository.deleteSemester(semesterId)
//        }
//    }




    fun addSubject(
        semesterId: Int,
        name: String = "",
        credits: Int = 0,
        grade: String = ""
    ) {
        viewModelScope.launch {
            repository.insertSubject(
                SubjectEntity(
                    semesterOwnerId = semesterId,
                    name = name,
                    credits = credits,
                    grade = grade
                )
            )
        }
    }

    fun updateSubject(subject: SubjectEntity) {
        viewModelScope.launch {
            repository.insertSubject(subject)
        }
    }

    fun deleteSubject(subjectId: Int) {
        viewModelScope.launch {
            repository.deleteSubject(subjectId)
        }
    }

}
