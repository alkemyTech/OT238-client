package com.melvin.ongandroid.viewmodel

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.domain.use_case.RegisterUseCase
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registrationUseCase: RegisterUseCase,
    )
    : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status
    val signUpUserCharging = MutableLiveData(false)

    fun registerUser(newUser: UserRegistrationRequest) {
        signUpUserCharging.postValue(true)
        viewModelScope.launch {
            val apiRegistrationResponse = registrationUseCase.registerUser(newUser)
            signUpUserCharging.postValue(false)
            if (apiRegistrationResponse.success) {
                _status.value = ApiStatus.SUCCESS
            } else{
                _status.value = ApiStatus.FAILURE
            }
        }
    }

    fun validateEmail(email:String): Boolean {
        return if (email.isEmpty()){

            false
        }else PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun validatePassword(password:String,):Boolean{
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

            false
        }else passwordRegex.matcher(password).matches()
    }
    fun confirmPassword(password: String, confirmPassword:String):Boolean{
        return if (password != confirmPassword){
            false
        }else !confirmPassword.isEmpty()

    }
    fun validateUserName(username:String):Boolean{
        return !(username.isEmpty() && username.isBlank())
    }

    fun validateFields(username:String, password:String, email:String, confirmPassword:String):Boolean{
        val checkUserName:Boolean = validateUserName(username)
        val checkPasswordConfirm:Boolean = confirmPassword(password,confirmPassword)
        val checkPassword:Boolean = validatePassword(password)
        val checkEmail:Boolean = validateEmail(email)

        if (checkUserName && checkEmail && checkPassword && checkPasswordConfirm) {
            return true
        }
        return false
    }


}
