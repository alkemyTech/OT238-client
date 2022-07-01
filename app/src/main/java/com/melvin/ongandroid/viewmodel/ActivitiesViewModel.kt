package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.activities.Activitie
import com.melvin.ongandroid.model.entities.slides.Slide
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val repository: ApiClient
) : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    private val _activitieList = MutableLiveData<List<Activitie>>()


    fun observerActivitieList(): MutableLiveData<List<Activitie>> {
        return _activitieList
    }

    fun setActivitie() {
        viewModelScope.launch {
            val response = repository.getActivities()
            if (response.success) {
                _activitieList.value = response.activitieList
                _status.value = ApiStatus.SUCCESS
            } else {
                _activitieList.value = emptyList()
                _status.value = ApiStatus.FAILURE
            }
        }
    }


}