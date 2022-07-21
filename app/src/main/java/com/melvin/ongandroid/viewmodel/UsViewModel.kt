package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.us.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsViewModel @Inject constructor(
    private val dataProvider: ApiClient
): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _membersList = MutableLiveData<List<Member>>()
    val membersList: LiveData<List<Member>>
        get() = _membersList

    fun getMembers() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val response = dataProvider.getMembers()
            if (response.success) {
                _membersList.value = response.data
                _status.value = ApiStatus.SUCCESS
            } else {
                _membersList.value = emptyList()
                _status.value = ApiStatus.FAILURE
            }
        }
    }
}