package com.melvin.ongandroid.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppData @Inject constructor(@ApplicationContext context: Context) {
    val SHARED_KEY = "apikey"

    val storage =  context.getSharedPreferences(SHARED_KEY, 0)

    fun saveKey(key: String) {
        storage.edit().putString(SHARED_KEY, key).apply()
    }

    fun getKey(): String? {
        return storage.getString(SHARED_KEY, "")
    }
}