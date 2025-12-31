package com.practice.edubond.feature.student.sgpa_cgpa.presentation.viewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.SgpaCgpaRepository
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.local.SgpaCgpaDatabase

class SgpaCgpaViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SgpaCgpaViewModel::class.java)) {

            val database = SgpaCgpaDatabase.getDatabase(context)
            val repository = SgpaCgpaRepository(
                database.semesterDao(),
                database.subjectDao()
            )

            @Suppress("UNCHECKED_CAST")
            return SgpaCgpaViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
