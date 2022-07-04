package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
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
    private val _slideList = MutableLiveData<List<Slide>>()
    private val _newsList = MutableLiveData<List<News>>()

    fun observerSlideList ():MutableLiveData<List<Slide>> {
            return this._slideList
    }

    fun observeNewsList (): MutableLiveData<List<News>>{
        return this._newsList
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

    fun getNews(){
        viewModelScope.launch {
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