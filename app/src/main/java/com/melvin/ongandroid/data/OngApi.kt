package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.*
import com.melvin.ongandroid.model.entities.activities.ActivitiesResponse
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
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

    @POST("login")
    suspend fun postLogin(
        @Body login: LoginRequest
    ): AuthMethodsResponse

    @GET("news")
    suspend fun getNews() : NewsResponse


    @GET("activities")
    suspend fun getActivities() : ActivitiesResponse
}