package com.melvin.ongandroid.view.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.melvin.ongandroid.databinding.ActivityMainBinding
import com.melvin.ongandroid.domain.di.NetworkBroadcast
import com.melvin.ongandroid.domain.di.NetworkStatusService
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Register Broadcast
        val networkFilter = IntentFilter()
        networkFilter.addAction("CONNECTIVITY_CHANGE")
        registerReceiver(NetworkBroadcast(), networkFilter)
    }


    override fun onResume() {
        startService(Intent(this, NetworkStatusService::class.java))
        super.onResume()
    }

    override fun onStop() {
        stopService(Intent(this, NetworkStatusService::class.java))
        super.onStop()
    }

}