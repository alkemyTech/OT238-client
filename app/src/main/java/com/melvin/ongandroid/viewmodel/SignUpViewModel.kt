package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.domain.use_case.RegisterUseCase
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registrationUseCase: RegisterUseCase): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    fun registerUser(newUser: UserRegistrationRequest) {
        viewModelScope.launch {
            val apiRegistrationResponse = registrationUseCase.registerUser(newUser)
            if (apiRegistrationResponse.success) {
                _status.value = ApiStatus.SUCCESS
            } else _status.value = ApiStatus.FAILURE
        }
    }
}
