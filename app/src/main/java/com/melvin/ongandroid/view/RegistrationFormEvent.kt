package com.melvin.ongandroid.view

sealed class RegistrationFormEvent {
    data class UserNameChanged(val userName:String): RegistrationFormEvent()
    data class EmailChanged(val email:String) : RegistrationFormEvent()
    data class PasswordChanged(val password:String): RegistrationFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword:String): RegistrationFormEvent()

    object Submit: RegistrationFormEvent()

}