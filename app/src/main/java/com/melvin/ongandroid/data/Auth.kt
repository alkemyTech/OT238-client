package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface Auth {
    @GET("auth/me")
    suspend fun getAuth(
        @Header("Authorization: Bearer ")
        token: String
    ): AuthMethodsResponse
}