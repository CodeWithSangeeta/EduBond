package com.practice.edubond.feature.auth.login

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object LoginClicked : LoginEvent()
    object GoogleLoginClicked : LoginEvent()
}



