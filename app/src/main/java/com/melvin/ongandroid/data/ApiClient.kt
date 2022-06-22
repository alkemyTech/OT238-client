package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.LoginRequest
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import dagger.Provides

import javax.inject.Inject

class ApiClient @Inject constructor(
    private val api: OngApi,
    private val logInApi: LogIn){

    suspend fun registerUser(newUser: UserRegistrationRequest): AuthMethodsResponse {
        return api.postNewUser(newUser)
    }
    suspend fun loginUser(loginRequest: LoginRequest): AuthMethodsResponse {
        return logInApi.postLogin(loginRequest)
    }
}