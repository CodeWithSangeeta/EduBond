package com.practice.edubond.feature.auth.signup

import com.practice.edubond.feature.auth.login.LoginEvent

sealed class SignupEvent {
    data class NameChanged(val value: String) : SignupEvent()
    data class EmailChanged(val value: String) : SignupEvent()
    data class PhoneChanged(val value: String) : SignupEvent()
    data class PasswordChanged(val value: String) : SignupEvent()
    data class ConfirmPasswordChanged(val value: String) : SignupEvent()
    data class RoleUpdated(val role: String?) : SignupEvent()
    data class TermsChecked(val checked: Boolean) : SignupEvent()
    object SignupClicked : SignupEvent()
    object GoogleSignupClicked : SignupEvent()
}
