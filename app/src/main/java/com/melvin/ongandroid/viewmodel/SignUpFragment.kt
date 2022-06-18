package com.melvin.ongandroid.viewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import java.util.regex.Pattern

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSignUp.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun validateEmail(): Boolean {
        val email = binding.tiUserEmail.editText?.text.toString()
        return if (email.isEmpty()) {
            binding.tiUserEmail.error = "Debes ingresar email"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tiUserEmail.error = "Ingresar un email valido"
            false
        } else {
            binding.tiUserEmail.error = null
            true
        }

    }

    private fun validatePassword(): Boolean {
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

    private fun validate(){
        val result = arrayOf(validateEmail(), validatePassword())
        if(false in result){
            return
        }
        binding.signUp_btn.enable = true

    }

}