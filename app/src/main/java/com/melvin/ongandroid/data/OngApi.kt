package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.*
import com.melvin.ongandroid.model.entities.contact.Contact
import com.melvin.ongandroid.model.entities.news.NewsResponse
import com.melvin.ongandroid.model.entities.whatWeDo.WhatWeDoResponse
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse
import com.melvin.ongandroid.model.entities.us.MembersResponse
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
    suspend fun getWhatWeDo() : WhatWeDoResponse

    @GET("members")
    suspend fun getMembers(): MembersResponse

    @POST("contacts")
    suspend fun postNewContact(
        @Body contact: Contact
    ):AuthMethodsResponse

}