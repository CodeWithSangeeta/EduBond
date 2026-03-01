package com.practice.edubond.feature.student.presentation.sgpa_cgpa.viewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.edubond.data.local.EduBondDatabase
import com.practice.edubond.feature.student.sgpa_cgpa.presentation.data.SgpaCgpaRepository

class SgpaCgpaViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SgpaCgpaViewModel::class.java)) {

            val database = EduBondDatabase.getDatabase(context)
            val repository = SgpaCgpaRepository(database)

            @Suppress("UNCHECKED_CAST")
            return SgpaCgpaViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
