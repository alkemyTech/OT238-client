package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.model.entities.News
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
): ViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    fun observeNewsList (): MutableLiveData<List<News>>{
        return this._newsList
    }

}