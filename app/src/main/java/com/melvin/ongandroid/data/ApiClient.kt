package com.melvin.ongandroid.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val api: OngApi = Retrofit.Builder()
        .baseUrl("http://ongapi.alkemy.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OngApi::class.java)
}