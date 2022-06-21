package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.RegistrationResponse
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiClient @Inject constructor(private val api: OngApi){

    suspend fun registerUser(newUser: UserRegistrationRequest): RegistrationResponse {
        return api.postNewUser(newUser)
    }
}