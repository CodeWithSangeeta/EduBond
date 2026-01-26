package com.practice.edubond.feature.auth.state

sealed interface AuthUiState {

    object Unauthenticated : AuthUiState

    object Loading : AuthUiState

    data class Authenticated(
        val userId: String,
        val role: UserRole
    ) : AuthUiState

    data class Error(
        val message: String
    ) : AuthUiState
}




enum class UserRole {
    STUDENT, TEACHER
}