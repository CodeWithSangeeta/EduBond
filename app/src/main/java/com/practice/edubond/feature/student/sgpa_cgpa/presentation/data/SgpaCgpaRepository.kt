package com.practice.edubond.feature.student.sgpa_cgpa.presentation.data


import com.practice.edubond.data.local.EduBondDatabase
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.dao.SemesterDao
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.dao.SubjectDao
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SemesterEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity
import kotlinx.coroutines.flow.Flow

class SgpaCgpaRepository(
  database : EduBondDatabase
) {
    private val semesterDao = database.semesterDao()
    private val subjectDao = database.subjectDao()
    /* ---------- SEMESTER ---------- */

    fun getAllSemesters(): Flow<List<SemesterEntity>> {
        return semesterDao.getAllSemesters()
    }

    suspend fun insertSemester(semester: SemesterEntity) {
        semesterDao.insertSemester(semester)
    }

    /* ---------- SUBJECT ---------- */

    fun getSubjectsForSemester(semesterId: Int): Flow<List<SubjectEntity>> {
        return subjectDao.getSubjectsForSemester(semesterId)
    }

    suspend fun insertSubject(subject: SubjectEntity) {
        subjectDao.insertSubject(subject)
    }

    suspend fun deleteSubject(subjectId: Int) {
        subjectDao.deleteSubject(subjectId)
    }
}
