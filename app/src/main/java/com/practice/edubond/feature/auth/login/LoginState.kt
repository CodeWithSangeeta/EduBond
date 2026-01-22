package com.practice.edubond.feature.auth.login


data class LoginState(
    val email: String = "",
    val password: String = "",
    val selectedRole: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)


