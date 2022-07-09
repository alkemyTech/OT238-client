package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestimonialsViewModel @Inject constructor(
    private val repository: ApiClient
) : ViewModel() {

    val _status = MutableLiveData<ApiStatus>()
    val _testimonialsList = MutableLiveData<List<Testimonials>>()

    fun observerTestimonialsList (): MutableLiveData<List<Testimonials>> {
        return _testimonialsList
    }

    fun getTestimonial(){
        viewModelScope.launch {
            val response = repository.getTestimonials()
            if (response.success) {
                _testimonialsList.value = response.testimonialsList
                _status.value = ApiStatus.SUCCESS
            } else {
                _testimonialsList.value = emptyList()
                _status.value = ApiStatus.FAILURE
            }
        }
    }

}

