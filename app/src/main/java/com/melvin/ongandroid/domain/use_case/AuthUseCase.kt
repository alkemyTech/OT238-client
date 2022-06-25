package com.melvin.ongandroid.domain.use_case

import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val dataProvider: ApiClient) {
    suspend fun authUser(authUser: String): AuthMethodsResponse {
        return dataProvider.authUser(authUser)
    }
}