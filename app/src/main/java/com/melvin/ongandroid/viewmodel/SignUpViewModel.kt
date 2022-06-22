package com.melvin.ongandroid.viewmodel


import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import com.melvin.ongandroid.domain.use_case.RegisterUseCase
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registrationUseCase: RegisterUseCase): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status
    private val emptyFieldMsg = "Este campo debe ser completado"
    private val invalidMsg = "El campo ingresado no es valido"
    private val passWordError = "Debe ser alfanumerico, contener una letra mayuscula, un caracter especial y sin espacios"
    private val passwordConfirmError = "Los passwords no coinciden"


    fun registerUser(newUser: UserRegistrationRequest) {
        viewModelScope.launch {
            val apiRegistrationResponse = registrationUseCase.registerUser(newUser)
            if (apiRegistrationResponse.success) {
                _status.value = ApiStatus.SUCCESS
            } else _status.value = ApiStatus.FAILURE
        }
    }

    fun validateEmail(email:String, emailMsg:TextInputLayout): Boolean {
        return if (email.isEmpty()){
            emailMsg.error = emptyFieldMsg
            false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            emailMsg.error = invalidMsg
            false
        }else{
            emailMsg.error = null
            true
        }
    }
    fun validatePassword(password:String, passwordMessage:TextInputLayout):Boolean{
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +        //at least 1 lower case letter
                    "(?=.*[A-Z])" +        //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$"
        )
        return if (password.isEmpty()){
            passwordMessage.error = emptyFieldMsg
            false
        }else if (!passwordRegex.matcher(password).matches()){
            passwordMessage.error = passWordError
            false
        }else{
            passwordMessage.error = null
            true
        }
    }
    fun confirmPassword(password: String, confirmPassword:String, passwordMessage: TextInputLayout):Boolean{
        return if (password != confirmPassword){
            passwordMessage.error = passwordConfirmError
            false
        }else if (confirmPassword.isEmpty()){
            passwordMessage.error = passwordConfirmError
            false
        }else{
            passwordMessage.error = null
            true
        }

    }
    fun validateUserName(username:String, usernameMessage:TextInputLayout ):Boolean{
        return if (username.isEmpty() && username.isBlank()){
            usernameMessage.error = emptyFieldMsg
            false
        }else{
            usernameMessage.error = null
            true
        }
    }

}
