package com.practice.edubond.feature.student.sgpa_cgpa.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.SgpaCgpaRepository
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Subject
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SemesterEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
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




    /* ---------- ACTIONS ---------- */

    fun addSemester() {
        viewModelScope.launch {
            val max = repository.getMaxSemesterNumber() ?: 0
            val semesterId = repository.insertSemester(
                SemesterEntity(semesterNumber = max + 1)
            )
            repository.insertSubject(
                SubjectEntity(
                    semesterOwnerId = semesterId.toInt(),
                    name = "",
                    credits = 0,
                    grade = ""
                )
            )
        }
    }

    fun deleteSemester(semesterId : Int) {
        viewModelScope.launch {
           repository.deleteSemester(semesterId)
        }
    }


    fun ensureAtLeastOneSemester() {
        viewModelScope.launch {
            val maxSemester = repository.getMaxSemesterNumber()
            if (maxSemester == null) {
                repository.insertSemester(
                    SemesterEntity(semesterNumber = 1)
                )
            }
        }
    }


    fun ensureAtLeastOneSubject(semesterId: Int) {
        viewModelScope.launch {
            repository.getSubjectsForSemester(semesterId)
                .first()
                .let { subjects ->
                    if (subjects.isEmpty()) {
                        repository.insertSubject(
                            SubjectEntity(
                                semesterOwnerId = semesterId,
                                name = "",
                                credits = 0,
                                grade = ""
                            )
                        )
                    }
                }
        }
    }


    fun getSubjectsForSemester(semesterId: Int): Flow<List<SubjectEntity>> {
        return repository.getSubjectsForSemester(semesterId)
    }

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


    fun updateSubject(subject:  SubjectEntity){
        viewModelScope.launch {
            repository.updateSubject(subject)
        }
    }

    fun deleteSubject(subjectId: Int) {
        viewModelScope.launch {
            repository.deleteSubject(subjectId)
        }
    }

}
