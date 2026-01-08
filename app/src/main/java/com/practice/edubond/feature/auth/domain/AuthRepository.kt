package com.practice.edubond.feature.auth.domain


interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun signup(email: String, password: String)
    fun isUserLoggedIn(): Boolean
}