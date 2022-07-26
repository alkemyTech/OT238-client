package com.melvin.ongandroid.domain.di

import android.content.Context
import android.content.Intent
import com.melvin.ongandroid.domain.di.ConnectionInternet.NetworkConnection.isConnected

class BroadcastMessages {

    fun sendConnectivityChange(context: Context, isConnected: Boolean) {
        Intent().also { intent ->
            intent.action = "CONNECTIVITY_CHANGE"
            intent.putExtra("STATUS", isConnected)
            context.sendBroadcast(intent)
        }
    }

    fun sendConnectivityCheck(context: Context) {
        val status = isConnected.value
        Intent().also { intent ->
            intent.action = "CONNECTIVITY_CHECK"
            intent.putExtra("STATUS", status)
            context.sendBroadcast(intent)
        }
    }
}