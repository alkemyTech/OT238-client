package com.melvin.ongandroid.domain.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow

class ConnectionInternet {

    object NetworkConnection {
        val isConnected = MutableStateFlow(false)
        val broadcastMessages = BroadcastMessages()

        fun initialize(context: Context) {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val request = NetworkRequest.Builder().build()
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: android.net.Network) {
                    isConnected.value = true
                    broadcastMessages.sendConnectivityChange(context, isConnected.value)
                }
                override fun onLost(network: android.net.Network) {
                    isConnected.value = false
                    broadcastMessages.sendConnectivityChange(context, isConnected.value)
                }
                override fun onUnavailable() {
                    isConnected.value = false
                    broadcastMessages.sendConnectivityCheck(context)
                }
            }
            cm.registerNetworkCallback(request, callback)
        }
    }
}
