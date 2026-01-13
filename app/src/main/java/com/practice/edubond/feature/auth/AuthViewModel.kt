package com.practice.edubond.feature.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _selectedRole = MutableStateFlow<String?>(null)
    val selectedRole: StateFlow<String?> = _selectedRole.asStateFlow()

    fun selectRole(role: String) {
        _selectedRole.value = role
    }

    fun clearRole() {
        _selectedRole.value = null
    }
}