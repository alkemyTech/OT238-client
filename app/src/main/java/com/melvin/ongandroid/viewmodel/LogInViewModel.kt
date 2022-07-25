package com.melvin.ongandroid.viewmodel

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.R
import com.melvin.ongandroid.data.AppData
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackLogInError
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackLogInSucces
import com.melvin.ongandroid.domain.use_case.LogInUseCase
import com.melvin.ongandroid.model.entities.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val appData: AppData,
    private val logInUseCase: LogInUseCase,
) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    fun validateEmail(Email: String?): Boolean {
        return if (Email != null) {
            Email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(Email).matches()
        } else {
            false
        }
    }

    fun validatePassword(password: String?): Boolean {
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
        return if (password != null) {
            (password.isNotEmpty() && passwordRegex.matcher(password).matches())
        } else {
            false
        }
    }

    fun logInUser(logIn: LoginRequest){
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            val apiLogIn = logInUseCase.logInUser(logIn)
            if (apiLogIn.success) {
                _status.value = ApiStatus.SUCCESS
                trackLogInSucces(R.string.logInSuccess.toString())
                appData.savePrefs("key", apiLogIn.data.token)
                apiLogIn.data.user.name?.let { appData.savePrefs("username", it) }
                apiLogIn.data.user.email?.let { appData.savePrefs("email", it) }
            } else {
                _status.value = ApiStatus.FAILURE
                trackLogInError(R.string.logInError.toString())
            }
        }

    }

    fun loginWithSocialMedia(status: String): Boolean {
        when(status){
            "LOGIN_ACTION" -> {
                _status.value = ApiStatus.LOADING
                return true
            }
            "LOGGED_IN" -> {
                _status.value = ApiStatus.SUCCESS
                return true
            }
            "FAILED_LOGIN" -> {
                _status.value = ApiStatus.FAILURE
                return false
            }
        }
        return true
    }
}