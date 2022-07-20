package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackMembersRetrieveError
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackMembersRetrieveSuccess
import com.melvin.ongandroid.model.entities.us.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsViewModel @Inject constructor(
    private val dataProvider: ApiClient
): ViewModel() {
    //TODO No olvidar usar el get
    val _status = MutableLiveData<ApiStatus>()
    val _membersList = MutableLiveData<List<Member>>()

    fun observeMembersList(): MutableLiveData<List<Member>> {
        return this._membersList
    }

    fun getMembers() {
        viewModelScope.launch {
            val response = dataProvider.getMembers()
            if (response.success) {
                _membersList.value = response.data
                _status.value = ApiStatus.SUCCESS
                trackMembersRetrieveSuccess("SUCCESS")
            } else {
                _membersList.value = emptyList()
                _status.value = ApiStatus.FAILURE
                trackMembersRetrieveError("ERROR")
            }
        }
    }
}