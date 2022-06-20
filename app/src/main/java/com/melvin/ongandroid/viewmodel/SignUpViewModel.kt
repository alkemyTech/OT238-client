package com.melvin.ongandroid.viewmodel



import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {

    /*fun validateFields(username: String, email: String, password: String, confirmPassword: String) {

        val fieldsEmpty: Boolean =
            username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
        val emailFormat: Boolean = validateEmail(email)
        val passwordsFormat: Boolean =
            validatePassword(password) && isValidPasswordFormat(confirmPassword)


    }*/

      fun validateEmail(email: TextInputEditText): Boolean {
        return if (email.toString().isEmpty()) {
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

     fun validatePassword(password: TextInputEditText): Boolean {
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
         return Pattern.matches(passwordRegex.toString(), password.toString())
    }



}