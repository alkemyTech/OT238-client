package com.melvin.ongandroid.domain.di

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NetworkStatusService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ConnectionInternet.NetworkConnection.initialize(applicationContext)
        return START_STICKY
    }
}