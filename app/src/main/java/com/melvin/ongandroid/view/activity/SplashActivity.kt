package com.melvin.ongandroid.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.melvin.ongandroid.data.AppData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity() : AppCompatActivity(){
    @Inject lateinit var appData : AppData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAuth()
    }

    private fun checkAuth(){
        val token = appData.getKey().toString()
        if(token != ""){
            startActivity(Intent(this, HomeActivity::class.java))
        }else{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}