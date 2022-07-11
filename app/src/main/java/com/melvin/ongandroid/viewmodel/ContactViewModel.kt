package com.melvin.ongandroid.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    fun validateName(name: String): Boolean {
        return name.isNotEmpty()
    }

    fun validateNumber(name: String): Boolean {
        return name.isNotEmpty() && name.isDigitsOnly()
    }

    fun validateEmail(Email: String): Boolean {
        return Email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(Email).matches()
    }

    fun validateMessage(name: String): Boolean {
        return name.isNotEmpty()
    }

}