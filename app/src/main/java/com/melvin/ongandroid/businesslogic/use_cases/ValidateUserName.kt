package com.melvin.ongandroid.businesslogic.use_cases

import javax.inject.Inject

class ValidateUserName @Inject constructor() {

    fun executeUserNameValidation(username:String): ValidationResult{
        if (username.isBlank() || username.length < 4){
            return ValidationResult(
                successful = false,
                errorMessage = "Debes completar este campo"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}