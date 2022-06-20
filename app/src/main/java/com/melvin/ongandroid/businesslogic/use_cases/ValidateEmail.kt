package com.melvin.ongandroid.businesslogic.use_cases

import android.util.Patterns
import javax.inject.Inject

class ValidateEmail @Inject constructor() {

    fun executeEmailValidation(email:String): ValidationResult{
        if (email.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Debes completar este campo"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Debes ingresar un correo valido"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}