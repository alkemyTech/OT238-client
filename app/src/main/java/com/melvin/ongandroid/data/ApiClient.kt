package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import javax.inject.Inject

class ApiClient @Inject constructor(private val api: OngApi){

    suspend fun registerUser(newUser: UserRegistrationRequest): AuthMethodsResponse {
        return api.postNewUser(newUser)
    }
}