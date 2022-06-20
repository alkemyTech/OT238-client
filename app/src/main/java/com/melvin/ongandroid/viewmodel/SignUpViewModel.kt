package com.melvin.ongandroid.viewmodel



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.businesslogic.use_cases.ValidateConfirmPassword
import com.melvin.ongandroid.businesslogic.use_cases.ValidateEmail
import com.melvin.ongandroid.businesslogic.use_cases.ValidatePassword
import com.melvin.ongandroid.businesslogic.use_cases.ValidateUserName
import com.melvin.ongandroid.view.RegistrationFormEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateUserName: ValidateUserName,
    private val validateConfirmPassword: ValidateConfirmPassword
) :ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent){
        when(event){
            is RegistrationFormEvent.EmailChanged ->{
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged ->{
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.ConfirmPasswordChanged ->{
                state = state.copy(confirmPassword = event.confirmPassword)
            }
            is RegistrationFormEvent.UserNameChanged ->{
                state = state.copy(userName = event.userName)
            }
            is RegistrationFormEvent.Submit ->{
                submitData()

            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.executeEmailValidation(state.email)
        val passwordResult = validatePassword.executePasswordValidation(state.password)
        val userNameResult = validateUserName.executeUserNameValidation(state.userName)
        val confirmPasswordResult = validateConfirmPassword.executeConfirmPasswordValidation(state.password, state.confirmPassword)

        val hasError = listOf(
            emailResult,
            passwordResult,
            userNameResult,
            confirmPasswordResult
        ).any { !it.successful }

        if(hasError){
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                userNameError = userNameResult.errorMessage,
                confirmPasswordError = confirmPasswordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }


}