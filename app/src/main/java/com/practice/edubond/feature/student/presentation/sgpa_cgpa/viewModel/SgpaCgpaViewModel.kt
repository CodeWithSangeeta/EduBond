package com.practice.edubond.feature.student.presentation.sgpa_cgpa.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.SgpaCgpaRepository
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.Subject
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.UiSubject
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SemesterEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SgpaCgpaViewModel(
    private val repository: SgpaCgpaRepository
) : ViewModel() {

    private val _uiSubjects =
        MutableStateFlow<Map<Int, List<UiSubject>>>(emptyMap())

    val uiSubjects: StateFlow<Map<Int, List<UiSubject>>> = _uiSubjects


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
                repository.insertSemester(
                    SemesterEntity(semesterNumber = max + 1)
                )

        }
    }

    fun deleteSemester(semesterId : Int) {
        viewModelScope.launch {
           repository.deleteSemester(semesterId)
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


    fun ensureUiSubjectForSemester(semesterId: Int) {
        if (_uiSubjects.value.containsKey(semesterId)) return

        _uiSubjects.value =
            _uiSubjects.value + (semesterId to listOf(UiSubject()))
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


    fun addUiSubject(semesterId: Int) {
        val current = _uiSubjects.value[semesterId] ?: emptyList()
        _uiSubjects.value =
            _uiSubjects.value + (semesterId to (current + UiSubject()))
    }


    fun updateUiSubject(
        semesterId: Int,
        index: Int,
        updated: UiSubject
    ) {
        val list = _uiSubjects.value[semesterId] ?: return

        _uiSubjects.value =
            _uiSubjects.value + (
                    semesterId to list.toMutableList().also {
                        it[index] = updated
                    }
                    )
    }


    fun deleteUiSubject(semesterId: Int, index: Int) {
        val list = _uiSubjects.value[semesterId] ?: return
        if (list.size <= 1) return

        _uiSubjects.value =
            _uiSubjects.value + (
                    semesterId to list.toMutableList().also {
                        it.removeAt(index)
                    }
                    )
    }



}
