package com.practice.edubond.feature.auth

sealed interface AuthUiState {

    object Unauthenticated : AuthUiState

    object Loading : AuthUiState

    data class Authenticated(
        val userId: String,
        val role: String
    ) : AuthUiState

    data class Error(
        val message: String
    ) : AuthUiState
}

