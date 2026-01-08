package com.practice.edubond.feature.auth.signup

sealed class SignupEvent {
    data class NameChanged(val value: String) : SignupEvent()
    data class EmailChanged(val value: String) : SignupEvent()
    data class PhoneChanged(val value: String) : SignupEvent()
    data class PasswordChanged(val value: String) : SignupEvent()
    data class ConfirmPasswordChanged(val value: String) : SignupEvent()
    data class RoleSelected(val role: String) : SignupEvent()
    object SignupClicked : SignupEvent()
}
