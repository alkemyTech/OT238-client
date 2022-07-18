package com.melvin.ongandroid.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.melvin.ongandroid.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppData @Inject constructor(@ApplicationContext context: Context) {
    private val path : String = context.getString(R.string.pref_user_data)

    private val prefs : SharedPreferences = context.getSharedPreferences(path, Activity.MODE_PRIVATE)


    fun savePrefs(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getPrefs(key: String): String? {
        return prefs.getString(key, "")
    }

    fun clearPrefs() {
        prefs.edit().clear().apply()
    }
}