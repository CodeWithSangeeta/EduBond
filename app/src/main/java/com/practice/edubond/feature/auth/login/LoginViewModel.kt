package com.practice.edubond.feature.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    //email-password-auth
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser != null){
            _loginState.value =LoginState.Authenticated
        }else{
            _loginState.value = LoginState.Unauthenticated
        }
    }

    //Email-password
    fun login(email : String, password : String){
        if(email.isEmpty() || password.isEmpty()){
            _loginState.value = LoginState.Error("Email and password can't be empty")
            return
        }
        _loginState.value =LoginState.Loading

        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _loginState.value = LoginState.Authenticated
            } catch(e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Something went wrong")
            }
        }
    }

}