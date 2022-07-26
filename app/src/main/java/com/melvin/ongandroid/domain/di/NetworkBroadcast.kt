package com.melvin.ongandroid.domain.di

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.annotation.CallSuper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NetworkBroadcast : BroadcastReceiver() {

    private val bMessages = BroadcastMessages()

    @CallSuper
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "CONNECTIVITY_CHANGE" -> {
                if (!intent.getBooleanExtra("STATUS", false)) {
                    val dialog = AlertDialog.Builder(context)
                    dialog.setTitle("No Internet Connection")
                    dialog.setMessage("Please check your internet connection")
                    dialog.setPositiveButton("Retry") { _, _ ->
                        Toast.makeText(context, "Retrying...", Toast.LENGTH_SHORT).show()
                        bMessages.sendConnectivityCheck(context)
                    }
                    dialog.setCancelable(false)
                    dialog.show()
                } else {
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
                }
            }
            "CONNECTIVITY_CHECK" -> {

                if (!intent.getBooleanExtra("STATUS", false)) {
                    bMessages.sendConnectivityChange(context, false)
                }else{
                    bMessages.sendConnectivityChange(context, true)
                }
            }
        }
    }
}