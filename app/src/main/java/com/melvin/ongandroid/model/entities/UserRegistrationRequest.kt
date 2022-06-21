package com.melvin.ongandroid.model.entities

data class UserRegistrationRequest(
    val name: String,
    val email: String,
    val password: String
)
