package com.practice.edubond.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.edubond.feature.auth.domain.AuthRepository
import com.practice.edubond.feature.auth.signup.SignupEvent
import com.practice.edubond.feature.auth.signup.SignupState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignupEvent) {
        when (event) {

            is SignupEvent.NameChanged ->
                _state.update { it.copy(name = event.value) }

            is SignupEvent.EmailChanged ->
                _state.update { it.copy(email = event.value) }

            is SignupEvent.PhoneChanged ->
                _state.update { it.copy(phone = event.value) }

            is SignupEvent.PasswordChanged ->
                _state.update { it.copy(password = event.value) }

            is SignupEvent.ConfirmPasswordChanged ->
                _state.update { it.copy(confirmPassword = event.value) }

            is SignupEvent.RoleSelected ->
                _state.update { it.copy(role = event.role) }

            SignupEvent.SignupClicked ->
                signup()
        }
    }

    private fun signup() {
        val s = state.value

        if (
            s.email.isBlank() ||
            s.password.isBlank() ||
            s.confirmPassword.isBlank()
        ) {
            _state.update { it.copy(error = "All fields are required") }
            return
        }

        if (s.password != s.confirmPassword) {
            _state.update { it.copy(error = "Passwords do not match") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                authRepository.signup(s.email, s.password)

                _state.update {
                    it.copy(isLoading = false, isSuccess = true)
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Signup failed"
                    )
                }
            }
        }
    }
}
