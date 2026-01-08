package com.practice.edubond.feature.auth.signup

data class SignupState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val role: String? = null,

    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)
