package com.melvin.ongandroid.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.melvin.ongandroid.R
import com.melvin.ongandroid.data.AppData
import com.melvin.ongandroid.domain.use_case.AuthUseCase
import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.AuthRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity() : AppCompatActivity(){
    @Inject lateinit var appData : AppData
    @Inject lateinit var auth : AuthUseCase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAuth()
    }

    fun checkAuth(){
        val token = appData.getKey().toString()
        if(token != ""){
            //TODO verificar key
            val responce = sendData(token)

        }else{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }



    fun sendData(token : String) {


    }


}