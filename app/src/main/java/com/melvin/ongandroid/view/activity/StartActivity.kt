package com.melvin.ongandroid.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.melvin.ongandroid.data.AppData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class StartActivity : AppCompatActivity(){
    @Inject lateinit var appData : AppData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent.getStringExtra("STATUS")
        start(intent)
    }
    private fun start(intent: String?){
        if(intent == "LOGOUT"){
            checkAuth()
        }else{
            timer()
        }
    }

    private fun timer(){
        runBlocking {
            launch {
                delay(TimeUnit.SECONDS.toMillis(5))
                checkAuth()
            }
        }
    }

    private fun checkAuth(){
        val token = appData.getPrefs("key")
        if (token != null) {
            if(token.isNotEmpty()){
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

}