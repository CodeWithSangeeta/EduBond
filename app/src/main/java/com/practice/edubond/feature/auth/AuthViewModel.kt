package com.practice.edubond.feature.auth

import androidx.lifecycle.ViewModel
import com.practice.edubond.feature.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState =
        MutableStateFlow<AuthUiState>(AuthUiState.Unauthenticated)
    val authState: StateFlow<AuthUiState> = _authState.asStateFlow()

    fun onLoginSuccess(
        userId: String,
        role: String
    ) {
        _authState.value = AuthUiState.Authenticated(
            userId = userId,
            role = role
        )
    }

    fun onSignupSuccess(
        userId: String,
        role: String
    ) {
        _authState.value = AuthUiState.Authenticated(
            userId = userId,
            role = role
        )
    }

    fun onLogout() {
        _authState.value = AuthUiState.Unauthenticated
    }
}
