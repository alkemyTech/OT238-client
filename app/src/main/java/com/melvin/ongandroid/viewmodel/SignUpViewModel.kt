package com.melvin.ongandroid.viewmodel

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.domain.analytics.AnalyticsSender
import com.melvin.ongandroid.domain.use_case.RegisterUseCase
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registrationUseCase: RegisterUseCase,
) : ViewModel() {

    val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status
    val signUpUserCharging = MutableLiveData(false)

    fun registerUser(newUser: UserRegistrationRequest) {
        signUpUserCharging.postValue(true)
        viewModelScope.launch {
            val apiRegistrationResponse = registrationUseCase.registerUser(newUser)
            signUpUserCharging.postValue(false)
            if (apiRegistrationResponse.success) {
                AnalyticsSender.trackEventSignUpSuccess("sign_up_succes")
                _status.value = ApiStatus.SUCCESS
            } else {
                _status.value = ApiStatus.FAILURE
                AnalyticsSender.trackEventSignUpError("sign_up_error")
            }
        }
    }

    fun validateEmail(Email: String?): Boolean {
        return if (Email != null) {
            Email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(Email).matches()
        } else {
            false
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
                    "$"
        )
        return password.isNotEmpty() && passwordRegex.matcher(password).matches()
    }

    fun confirmPassword(password: String, confirmPassword: String): Boolean {
        return if (password != confirmPassword) {
            false
        } else confirmPassword.isNotEmpty()

    }

    fun validateUserName(username: String?): Boolean {
        return if (username != null) {
            return !(username.isEmpty() && username.isBlank())

            } else {
                false
            }
    }

    fun validateFields(
        username: String,
        password: String,
        email: String,
        confirmPassword: String
    ): Boolean {
        val checkUserName: Boolean = validateUserName(username)
        val checkPasswordConfirm: Boolean = confirmPassword(password, confirmPassword)
        val checkPassword: Boolean = validatePassword(password)
        val checkEmail: Boolean = validateEmail(email)

        if (checkUserName && checkEmail && checkPassword && checkPasswordConfirm) {
            return true
        }
        return false
    }


}
