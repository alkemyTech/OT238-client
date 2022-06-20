package com.melvin.ongandroid.businesslogic.use_cases

import javax.inject.Inject


class ValidateConfirmPassword @Inject constructor(){

    fun executeConfirmPasswordValidation(password: String, repeatedPassword:String): ValidationResult {
        if (password != repeatedPassword ) {
            return ValidationResult(
                successful = false,
                errorMessage = "Los passwords no coinciden"
            )
        }

        return ValidationResult(
            successful = true
        )
    }


}