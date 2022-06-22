package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LogIn {

    @POST("login")
    suspend fun postLogin(
        @Body login: LoginRequest
    ): AuthMethodsResponse
}