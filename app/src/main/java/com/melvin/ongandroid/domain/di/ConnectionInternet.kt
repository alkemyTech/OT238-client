package com.melvin.ongandroid.domain.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow

class ConnectionInternet {

    object NetworkConnection {
        val isConnected = MutableStateFlow(false)

        fun initialize(context: Context) {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val request = NetworkRequest.Builder().build()
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: android.net.Network) {
                    isConnected.value = true
                    Toast.makeText(context, "connection OK", Toast.LENGTH_LONG).show()
                }
                override fun onLost(network: android.net.Network) {
                    isConnected.value = false
                    Toast.makeText(context, "connection FAIL", Toast.LENGTH_LONG).show()
                }
            }
            cm.registerNetworkCallback(request, callback)
        }

    }
} 