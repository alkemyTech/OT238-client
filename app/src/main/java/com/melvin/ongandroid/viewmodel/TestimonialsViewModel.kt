package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackTestimoniesRetrieveError
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackTestimoniesRetrieveSuccess
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestimonialsViewModel @Inject constructor(
    private val repository: ApiClient
) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _testimonialsList = MutableLiveData<List<Testimonials>>()
    val testimonialsList: LiveData<List<Testimonials>>
        get() = _testimonialsList

    fun getTestimonial(){
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val response = repository.getTestimonials()
            if (response.success) {
                _testimonialsList.value = response.testimonialsList
                _status.value = ApiStatus.SUCCESS
                trackTestimoniesRetrieveSuccess("SUCCESS")
            } else {
                _testimonialsList.value = emptyList()
                _status.value = ApiStatus.FAILURE
                trackTestimoniesRetrieveError("ERROR")
            }
        }
    }

}

