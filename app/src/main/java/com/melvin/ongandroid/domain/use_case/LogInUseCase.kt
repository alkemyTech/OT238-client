package com.melvin.ongandroid.domain.use_case

import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.LoginRequest
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val dataProvider: ApiClient) {
    suspend fun logInUser(logInUser : LoginRequest): AuthMethodsResponse {
        return dataProvider.loginUser(logInUser)
    }
}
