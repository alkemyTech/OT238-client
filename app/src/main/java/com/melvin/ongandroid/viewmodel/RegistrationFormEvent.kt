package com.melvin.ongandroid.viewmodel

data class RegistrationFormState(
    val userName: String = "",
    val userNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null

)