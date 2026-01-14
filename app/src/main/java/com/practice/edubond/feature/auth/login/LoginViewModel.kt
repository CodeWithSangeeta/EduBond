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
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    init {
        if (authRepository.isUserLoggedIn()) {
            _state.update { it.copy(isAuthenticated = true) }
        }
    }


    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email,error  = null) }
            }

            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password,error = null) }
            }

            is LoginEvent.RoleUpdated -> {
                _state.update { it.copy(selectedRole = event.role) }
            }

            LoginEvent.LoginClicked -> {
                login()
            }

            LoginEvent.GoogleLoginClicked -> {
                // will add later
            }
        }
    }

    private fun login() {
        val email = state.value.email.trim()
        val password = state.value.password

        //email empty
        if (email.isEmpty()) {
            _state.update { it.copy(error = "Email cannot be empty!") }
            return
        }

        //email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.update { it.copy(error = "Enter a valid email") }
            return
        }
        // Password empty
        if (password.isEmpty()) {
            _state.update { it.copy(error = "Password cannot be empty!") }
            return
        }
        //Password length validation (NEW)
        if (password.length < 6) {
            _state.update { it.copy(error = "Password must be at least 6 characters") }
            return
        }

        if (state.value.isLoading) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                authRepository.login(email, password)
                _state.update {
                    it.copy(
                        isLoading = false,
                        isAuthenticated = true
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Invalid Email or Password"
                    )
                }
            }
        }
    }
}
