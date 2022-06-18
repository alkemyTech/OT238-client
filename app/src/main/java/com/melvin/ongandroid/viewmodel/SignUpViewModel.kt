package com.melvin.ongandroid.viewmodel


import android.view.View
import androidx.core.util.PatternsCompat

import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {


    fun validateFields(username:String, email:String, password:String, confirmPassword:String): Boolean{
        return if (email.isEmpty() && password.isEmpty()) {
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

     fun validateEmail(email: View): Boolean {
       // val email = binding.tiUserEmail.editText?.text.toString()
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

     fun validatePassword(): Boolean {
        val password = binding.tiUserPassword.editText?.text.toString()
        val confirmPassword = binding.tiUserPasswordConfirm.editText?.text.toString()
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
        return if (password.isEmpty()) {
            binding.tiUserPassword.error = "Debes ingresar un password"
            false
        } else if (!passwordRegex.matcher(password).matches()) {
            binding.tiUserPassword.error = "Password es muy debil"
            false
        } else (password) {
            binding.tiUserPassword.error = null
            true
        }
    }

     fun validate(){
        val result = arrayOf(validateEmail(), validatePassword())
        if(false in result){
            return
        }
        binding.btnSignUp.enable = true

    }

}