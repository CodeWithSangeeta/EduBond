package com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SemesterEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.entities.SubjectEntity
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.dao.SemesterDao
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.dao.SubjectDao

@Database(
    entities = [
        SemesterEntity::class,
        SubjectEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SgpaCgpaDatabase : RoomDatabase() {

    abstract fun semesterDao(): SemesterDao
    abstract fun subjectDao(): SubjectDao

    companion object {

        @Volatile
        private var INSTANCE: SgpaCgpaDatabase? = null

        fun getDatabase(context: Context): SgpaCgpaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SgpaCgpaDatabase::class.java,
                    "sgpa_cgpa_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
