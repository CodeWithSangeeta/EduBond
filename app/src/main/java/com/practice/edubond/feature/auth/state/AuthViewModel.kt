package com.practice.edubond.feature.auth.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.edubond.feature.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState =
        MutableStateFlow<AuthUiState>(AuthUiState.Loading)
    val authState: StateFlow<AuthUiState> = _authState.asStateFlow()

    fun onLoginSuccess(
        userId: String,
        role: String
    ) {
        _authState.value = AuthUiState.Authenticated(
            userId = userId,
            role = UserRole.valueOf(role)
        )
    }

    // In AuthViewModel.kt, add init block:
    init {
        viewModelScope.launch {
            val user = authRepository.isUserLoggedIn()
            if (user) {
                // TODO: Fetch role from Firestore by user.uid
                _authState.value = AuthUiState.Authenticated("tempUid", UserRole.STUDENT) // placeholder
            } else {
                _authState.value = AuthUiState.Unauthenticated
            }
        }
    }


    fun onSignupSuccess(
        userId: String,
        role: String
    ) {
        _authState.value = AuthUiState.Authenticated(
            userId = userId,
            role = UserRole.valueOf(role)
        )
    }

    fun onLogout() {
        _authState.value = AuthUiState.Unauthenticated
    }

    fun onError(message: String) {
        _authState.value = AuthUiState.Error(message)
    }
}