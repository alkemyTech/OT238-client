package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.slider.Slider
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.data.OngApi
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
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

    private fun getSlides() {
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