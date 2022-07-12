package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.*
import com.melvin.ongandroid.model.entities.contact.Contact
import com.melvin.ongandroid.model.entities.news.NewsResponse
import com.melvin.ongandroid.model.entities.whatWeDo.WhatWeDoResponse
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse
import com.melvin.ongandroid.model.entities.us.MembersResponse

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

    suspend fun getNews(): NewsResponse {
        return api.getNews()
    }

    suspend fun getTestimonials(): TestimonialsResponse {
        return api.getTestimonials()
    }

    suspend fun getActivities () : WhatWeDoResponse {
        return api.getWhatWeDo()
    }

    suspend fun getMembers(): MembersResponse {
        return api.getMembers()
    }

    suspend fun createContact(contact: Contact): AuthMethodsResponse {
        return api.postNewContact(contact)
    }

}