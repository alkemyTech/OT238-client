package com.melvin.ongandroid.data

import com.melvin.ongandroid.model.entities.*
import com.melvin.ongandroid.model.entities.activities.ActivitiesResponse
import com.melvin.ongandroid.model.entities.slides.SlidesResponse

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

    suspend fun getActivities () : ActivitiesResponse {
        return api.getActivities()
    }
}