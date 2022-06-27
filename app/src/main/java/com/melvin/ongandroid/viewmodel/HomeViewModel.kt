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
    sealed class SlideStatus {
        class Success(val slideList: List<Slide>) : SlideStatus()
        class Failure(val emptyList: Any = emptyList<Slide>()) : SlideStatus()
    }

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status
    private val _slideList = MutableLiveData<SlideStatus>()
    val slideList: LiveData<SlideStatus>
        get() = _slideList


    /*private fun getSlides() {
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

    }*/

    fun getSlide() {
        viewModelScope.launch {
            val response = repository.getSlide()
            if (response.success) {
                _slideList.value = SlideStatus.Success(response.slideList)
            } else {
                _slideList.value = SlideStatus.Success(emptyList())
            }


        }


        /* private fun getSlides() {
             var slideActivities: SlidesResponse
             viewModelScope.launch {

                     slideActivities = apiSlides.getSlides()
                     if (slideActivities.success) {
                         _status.value = ApiStatus.SUCCESS
                         _slideList.value = slideActivities
                     } else {
                         _status.value = ApiStatus.FAILURE
                         _slideList.value = emptyList<SlidesResponse>()

                     }

             }
         }*/
    }
}