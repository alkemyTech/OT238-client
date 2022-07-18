package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackNewsRetrieveError
import com.melvin.ongandroid.domain.analytics.AnalyticsSender.Companion.trackNewsRetrieveSuccess
import com.melvin.ongandroid.model.entities.news.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val dataProvider: ApiClient
) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    private val _newsList = MutableLiveData<List<News>>()

    fun observerNewsList() : MutableLiveData<List<News>> {
        return this._newsList
    }

    fun getNews(){
        viewModelScope.launch {
            val response = dataProvider.getNews()
            if (response.success) {
                _newsList.value = response.data
                _status.value = ApiStatus.SUCCESS
                trackNewsRetrieveSuccess("SUCCESS")
            } else {
                _newsList.value = emptyList()
                _status.value = ApiStatus.FAILURE
                trackNewsRetrieveError("ERROR")
            }
        }
    }

}