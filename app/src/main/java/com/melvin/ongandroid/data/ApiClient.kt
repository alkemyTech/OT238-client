package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.LoginRequest
import com.melvin.ongandroid.model.entities.NewsResponse
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse

import javax.inject.Inject

class ApiClient @Inject constructor(
    private val api: OngApi,
) {

    suspend fun registerUser(newUser: UserRegistrationRequest): AuthMethodsResponse {
        return api.postNewUser(newUser)
    }

    suspend fun loginUser(loginRequest: LoginRequest): AuthMethodsResponse {
        return api.postLogin(loginRequest)
    }

    suspend fun getSlide(): SlidesResponse {
        return api.getSlides()
    }

    suspend fun getNews(): NewsResponse{
        return api.getNews()
    }

    suspend fun getTestimonials(): TestimonialsResponse{
        return api.getTestimonials()
    }
}