package com.melvin.ongandroid.domain.use_case

import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.contact.Contact
import javax.inject.Inject

class CreateContactUseCase @Inject constructor(private val dataProvider: ApiClient){
    suspend fun createContact(newContact: Contact): AuthMethodsResponse {
        return dataProvider.createContact(newContact)
    }
}

