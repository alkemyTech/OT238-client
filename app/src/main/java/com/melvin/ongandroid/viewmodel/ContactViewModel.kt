package com.melvin.ongandroid.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.domain.use_case.CreateContactUseCase

import com.melvin.ongandroid.model.entities.contact.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject
@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactUseCase: CreateContactUseCase,
) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status
    val createContactCharging = MutableLiveData(false)

    fun validateName(name: String): Boolean {
        val nameRegex = Pattern.compile("^([^0-9]*)$")
        return if (!name.isNullOrEmpty()) (nameRegex.matcher(name).matches() && name.contains(" ")) else false
    }

    fun validateNumber(name: String): Boolean {
        return name.isNotEmpty() && name.isDigitsOnly()
    }

    fun validateEmail(Email: String): Boolean {
        return Email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(Email).matches()
    }

    fun validateMessage(name: String): Boolean {
        return name.isNotEmpty()
    }

    fun createContact(newContact: Contact) {
        createContactCharging.postValue(true)
        viewModelScope.launch {
            val apiCreateContact=contactUseCase.createContact(newContact)
            createContactCharging.postValue(false)
            if(apiCreateContact.success){
                _status.value = ApiStatus.SUCCESS
            }else{
                _status.value = ApiStatus.FAILURE
            }
        }
    }
}