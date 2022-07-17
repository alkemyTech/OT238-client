package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.*
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.news.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val dataProvider: ApiClient
) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>>
        get() = _newsList

    fun getNews(){
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val response = dataProvider.getNews()
            if (response.success) {
                _newsList.value = response.data
                _status.value = ApiStatus.SUCCESS
            } else {
                _newsList.value = emptyList()
                _status.value = ApiStatus.FAILURE
            }
        }
    }

}