package com.melvin.ongandroid.viewmodel



import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {

    fun validateFields(username: String, email: String, password: String, confirmPassword: String) {

        val fieldsEmpty: Boolean =
            username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
        val emailFormat: Boolean = validateEmail(email)
        val passwordsFormat: Boolean =
            validatePassword(password) && isValidPasswordFormat(confirmPassword)


    }

      fun validateEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            email.error = "Debes ingresar email"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email.toString()).matches()) {
            email.error = "Ingresar un email valido"
            false
        } else {
            email.error = null
            true
        }

    }

     fun validatePassword(password: String): Boolean {
         val passwordRegex = Pattern.compile(
             "^" +
                     "(?=.*[0-9])" +         //at least 1 digit
                     "(?=.*[a-z])" +        //at least 1 lower case letter
                     "(?=.*[A-Z])" +        //at least 1 upper case letter
                     "(?=.*[@#$%^&+=])" +    //at least 1 special character
                     "(?=\\S+$)" +           //no white spaces
                     ".{4,}" +               //at least 4 characters
                     "$",
         )
         return Pattern.matches(passwordRegex.toString(), password)
    }

     fun validate(){
        val result = arrayOf(validateEmail(), validatePassword())
        if(false in result){
            return
        }
        binding.btnSignUp.isEnabled = true

    }

}