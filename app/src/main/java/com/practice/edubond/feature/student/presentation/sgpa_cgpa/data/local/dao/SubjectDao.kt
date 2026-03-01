package com.practice.edubond.feature.student.presentation.sgpa_cgpa.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practice.edubond.feature.student.presentation.sgpa_cgpa.data.local.entities.SubjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: SubjectEntity)

    @Query("SELECT * FROM subjects WHERE semesterOwnerId = :semesterId")
    fun getSubjectsForSemester(semesterId: Int): Flow<List<SubjectEntity>>

    @Query("DELETE FROM subjects WHERE subjectId = :subjectId")
    suspend fun deleteSubject(subjectId: Int)
}
