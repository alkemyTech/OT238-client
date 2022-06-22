package com.melvin.ongandroid.model.entities

data class RegistrationResponse(
    val success: Boolean,
    val data: DataResponse,
    val message: String
)
