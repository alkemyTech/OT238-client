package com.melvin.ongandroid.domain.use_case

import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.RegistrationResponse
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val dataProvider: ApiClient){

    suspend fun registerUser(newUser: UserRegistrationRequest) : RegistrationResponse {
        return dataProvider.registerUser(newUser)
    }
}