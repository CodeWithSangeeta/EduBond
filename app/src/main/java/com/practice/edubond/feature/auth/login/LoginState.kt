package com.practice.edubond.feature.auth.login

sealed class LoginState {
    object Authenticated : LoginState()
    object Unauthenticated : LoginState()
    object Loading : LoginState()
    data class Error(val message : String) : LoginState()
    }

