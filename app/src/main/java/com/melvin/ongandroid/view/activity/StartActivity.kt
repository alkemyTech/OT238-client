package com.melvin.ongandroid.view.activity

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.melvin.ongandroid.data.AppData
import com.melvin.ongandroid.domain.di.BroadcastMessages
import com.melvin.ongandroid.domain.di.NetworkBroadcast
import com.melvin.ongandroid.domain.di.NetworkStatusService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class StartActivity : AppCompatActivity(){
    @Inject lateinit var appData : AppData
    private val broadcast = BroadcastMessages()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Start NetworkService
        startService(Intent(this, NetworkStatusService::class.java))
        //Register Broadcast
        val networkFilter = IntentFilter()
        networkFilter.addAction("CONNECTIVITY_CHANGE")
        registerReceiver(NetworkBroadcast(), networkFilter)
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