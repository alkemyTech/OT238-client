package com.melvin.ongandroid.domain.di

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow

class ConnectionInternet {

    companion object{
//        private fun checkForInternet(context: Context): Boolean {
//
//            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//                // Returns a Network object corresponding to
//                // the currently active default data network.
//                val network = connectivityManager.activeNetwork ?: return false
//
//                // Representation of the capabilities of an active network.
//                val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
//
//                return when {
//                    // Indicates this network uses a Wi-Fi transport,
//                    // or WiFi has network connectivity
//                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//
//                    // Indicates this network uses a Cellular transport. or
//                    // Cellular has network connectivity
//                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//
//                    // else return false
//                    else -> false
//                }
//            } else {
//                // if the android version is below M
//                @Suppress("DEPRECATION") val networkInfo =
//                    connectivityManager.activeNetworkInfo ?: return false
//                @Suppress("DEPRECATION")
//                return networkInfo.isConnected
//            }
//        }
//
//
//        fun ValidateConection(context: Context) : Boolean {
//            if (checkForInternet(context)) {
//                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
//                return true
//            } else {
//                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
//                return false
//            }
//        }
    }

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