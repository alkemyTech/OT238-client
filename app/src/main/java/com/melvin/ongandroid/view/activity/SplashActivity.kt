package com.melvin.ongandroid.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.melvin.ongandroid.data.AppData
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : AppCompatActivity(){
    @Inject lateinit var appData : AppData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer()
    }

    private fun timer(){
        val timer = Executors.newSingleThreadScheduledExecutor()
        timer.schedule(
            {
                runOnUiThread {
                    checkAuth()
                    Toast.makeText(this, "Timer has finished", Toast.LENGTH_LONG).show()
                    finish()
                }
            },5,TimeUnit.SECONDS)
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