package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.*
import com.melvin.ongandroid.model.entities.whatWeDo.WhatWeDoResponse
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse
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

    @GET("testimonials")
    suspend fun getTestimonials() : TestimonialsResponse

    @GET("activities")
    suspend fun getActivities() : WhatWeDoResponse

}