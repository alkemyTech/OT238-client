package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackSliderRetrieveError
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackSliderRetrieveSuccess
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.model.entities.news.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApiClient
) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _slideList = MutableLiveData<List<Slide>>()
    val slideList: LiveData<List<Slide>>
        get() = _slideList

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>>
        get() = _newsList

    fun getSlides() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val response = repository.getSlide()
            if (response.success) {
                _slideList.value = response.slideList
                _status.value = ApiStatus.SUCCESS
                trackSliderRetrieveSuccess("SUCCESS")
            } else {
                _slideList.value = emptyList()
                _status.value = ApiStatus.FAILURE
                trackSliderRetrieveError("ERROR")
            }
        }
    }

    fun getNews(){
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val response = repository.getNews()
            if (response.success){
                _newsList.value = response.data
                _status.value = ApiStatus.SUCCESS
            }else{
                _newsList.value = emptyList()
                _status.value = ApiStatus.FAILURE
            }
        }
    }
}