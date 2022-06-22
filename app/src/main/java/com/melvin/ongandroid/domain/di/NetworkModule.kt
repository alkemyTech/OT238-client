package com.melvin.ongandroid.domain.di

import com.melvin.ongandroid.data.LogIn
import com.melvin.ongandroid.data.OngApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://ongapi.alkemy.org/api/"

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOngApi(retrofit: Retrofit) : OngApi {
        return retrofit.create(OngApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLogInApi(retrofit: Retrofit) : LogIn {
        return retrofit.create(LogIn::class.java)
    }
}