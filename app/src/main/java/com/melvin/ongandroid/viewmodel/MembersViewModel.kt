package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import kotlinx.coroutines.launch
import javax.inject.Inject

class MembersViewModel @Inject constructor(
    private val dataProvider: ApiClient
): ViewModel() {


    fun getMembers() {
        viewModelScope.launch {
            val response = dataProvider.getMembers()
        }
    }
}