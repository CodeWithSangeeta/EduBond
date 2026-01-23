package com.practice.edubond.feature.auth.state

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AuthFormViewModel @Inject constructor() : ViewModel() {

    private val _selectedRole = MutableStateFlow<String?>(null)
    val selectedRole = _selectedRole.asStateFlow()

    fun updateRole(role: String?) {
        _selectedRole.value = role
    }

    fun clear() {
        _selectedRole.value = null
    }
}
