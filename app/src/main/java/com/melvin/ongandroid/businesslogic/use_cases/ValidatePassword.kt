package com.melvin.ongandroid.businesslogic.use_cases


import javax.inject.Inject


class ValidatePassword @Inject constructor() {

    fun executePasswordValidation(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "El password debe contener 8 caracteres minimo"
            )
        }
        val containsLettersAndDigits =
            password.any { it.isLetterOrDigit() } /*&& password.any { it.isLetter() }*/
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Debe ser alfanumerico"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}