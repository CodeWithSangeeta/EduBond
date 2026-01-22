package com.practice.edubond.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.edubond.feature.auth.domain.AuthRepository
import com.practice.edubond.feature.auth.login.LoginEvent
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
    private val _signupSuccess = MutableStateFlow<Pair<String, String>?>(null)
    val signupSuccess = _signupSuccess.asStateFlow()


    fun onEvent(event: SignupEvent) {
        when (event) {

            is SignupEvent.NameChanged ->
                _state.update { it.copy(name = event.value,error = null) }

            is SignupEvent.EmailChanged ->
                _state.update { it.copy(email = event.value,error = null) }

            is SignupEvent.PhoneChanged ->
                _state.update { it.copy(phone = event.value,error = null) }

            is SignupEvent.PasswordChanged ->
                _state.update { it.copy(password = event.value,error = null) }

            is SignupEvent.ConfirmPasswordChanged ->
                _state.update { it.copy(confirmPassword = event.value,error = null) }

            is SignupEvent.RoleUpdated ->
                _state.update { it.copy(role = event.role) }

            is SignupEvent.TermsChecked -> {
                _state.update { it.copy(isTermsChecked = event.checked) }
            }

            SignupEvent.SignupClicked ->
                signup()
        }
    }

    private fun signup() {
        val s = state.value

        //  Name
        if (s.name.isBlank()) {
            _state.update { it.copy(error = "Name cannot be empty") }
            return
        }

        //  Email
        if (s.email.isBlank()) {
            _state.update { it.copy(error = "Email cannot be empty") }
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.email).matches()) {
            _state.update { it.copy(error = "Enter a valid email") }
            return
        }

        //  Phone (10 digits)
        if (s.phone.length != 10 || !s.phone.all { it.isDigit() }) {
            _state.update { it.copy(error = "Enter a valid 10-digit phone number") }
            return
        }

        //Password
        if (s.password.length < 6) {
            _state.update { it.copy(error = "Password must be at least 6 characters") }
            return
        }

        // Confirm password
        if (s.password != s.confirmPassword) {
            _state.update { it.copy(error = "Passwords do not match") }
            return
        }

        // Role
        if (s.role == null) {
            _state.update { it.copy(error = "Please select a role") }
            return
        }

        // Terms checkbox
        if (!s.isTermsChecked) {
            _state.update { it.copy(error = "Please accept Terms & Conditions") }
            return
        }
        // All validation passed → Firebase call
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                authRepository.signup(s.email, s.password)

                _signupSuccess.value = Pair(s.name, s.role)

                _state.update { it.copy(isLoading = false) }


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
