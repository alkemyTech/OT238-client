package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.data.OngApi
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiSlides:OngApi
): ViewModel() {

    init {
        getSlides()
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _slideList = MutableLiveData<SlidesResponse>()
    val slideList : LiveData<SlidesResponse>
        get() = _slideList

    private fun getSlides(){
        var slideActivities : SlidesResponse
        viewModelScope.launch {
         try {
             slideActivities = apiSlides.getSlides()
             _slideList.value = slideActivities
         }catch (e:Exception){
             slideActivities = SlidesResponse(false, emptyList())
             _slideList.value = slideActivities
         }
        }
    }
}