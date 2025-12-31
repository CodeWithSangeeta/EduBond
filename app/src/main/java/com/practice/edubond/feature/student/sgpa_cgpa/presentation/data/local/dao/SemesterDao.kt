package com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SemesterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SemesterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSemester(semester: SemesterEntity)

    @Query("SELECT * FROM semesters ORDER BY semesterNumber ASC")
    fun getAllSemesters(): Flow<List<SemesterEntity>>
}
