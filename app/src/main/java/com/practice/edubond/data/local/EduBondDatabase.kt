package com.practice.edubond.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SemesterEntity

import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.dao.SemesterDao
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.dao.SubjectDao

@Database(
    entities = [
        // SGPA–CGPA feature
        SemesterEntity::class,
        SubjectEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EduBondDatabase : RoomDatabase() {

    // SGPA–CGPA DAOs
    abstract fun semesterDao(): SemesterDao
    abstract fun subjectDao(): SubjectDao

    companion object {
        @Volatile
        private var INSTANCE: EduBondDatabase? = null

        fun getDatabase(context: Context): EduBondDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EduBondDatabase::class.java,
                    "edubond_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
