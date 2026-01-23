package com.practice.edubond.feature.auth.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.edubond.feature.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()
    private val _loginSuccess = MutableStateFlow<Pair<String, String>?>(null)
    val loginSuccess = _loginSuccess.asStateFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email, error = null) }
            }

            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password, error = null) }
            }

            is LoginEvent.RoleUpdated -> {
                _state.update { it.copy(selectedRole = event.role) }
            }

            LoginEvent.LoginClicked -> {
                login()
            }

            LoginEvent.GoogleLoginClicked -> {
                // next step
            }
        }
    }

    private fun login() {
        val email = state.value.email.trim()
        val password = state.value.password
        val role = state.value.selectedRole

        // validations (same as before)
        if (email.isEmpty()) {
            _state.update { it.copy(error = "Email cannot be empty") }
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.update { it.copy(error = "Enter a valid email") }
            return
        }

        if (password.length < 6) {
            _state.update { it.copy(error = "Password must be at least 6 characters") }
            return
        }

        if (role == null) {
            _state.update { it.copy(error = "Please select role") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                val userId = authRepository.login(email, password)

                // 🔥 THIS IS THE CONNECTION POINT
                _loginSuccess.value = Pair(userId, role)



                _state.update { it.copy(isLoading = false) }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Login failed"
                    )
                }
            }
        }
    }
}
