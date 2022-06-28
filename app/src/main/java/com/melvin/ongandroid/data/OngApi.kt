package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OngApi {

    @POST("register")
    suspend fun postNewUser(
        @Body newUser: UserRegistrationRequest
    ) : AuthMethodsResponse

    @GET("slides")
    suspend fun getSlides(): SlidesResponse
}