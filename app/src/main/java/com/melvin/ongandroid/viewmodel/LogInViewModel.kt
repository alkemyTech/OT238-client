package com.melvin.ongandroid.viewmodel

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.AppData
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

    fun logInUser(logIn: LoginRequest) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val apiLogIn = logInUseCase.logInUser(logIn)
            if (apiLogIn.success) {
                _status.value = ApiStatus.SUCCESS
                appData.saveKey(apiLogIn.data.token)
            } else {
                _status.value = ApiStatus.FAILURE
            }
        }
    }
}