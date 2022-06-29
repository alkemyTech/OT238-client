package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.slides.Slide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApiClient
) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    private val _slideList = MutableLiveData<List<Slide>>()


    fun observerSlideList ():MutableLiveData<List<Slide>> {
            return this._slideList
    }

    fun getSlides() {
        viewModelScope.launch {
            val response = repository.getSlide()
            if (response.success) {
                _slideList.value = response.slideList
                _status.value = ApiStatus.SUCCESS
            } else {
                _slideList.value = emptyList()
                _status.value = ApiStatus.FAILURE
            }
        }
    }

}