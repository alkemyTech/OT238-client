package com.melvin.ongandroid.model.entities

data class AuthMethodsResponse(
    val success: Boolean,
    val data: DataResponse,
    val message: String?
)
