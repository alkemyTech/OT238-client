package com.melvin.ongandroid.domain.di

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.CallSuper
import com.melvin.ongandroid.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NetworkBroadcast : BroadcastReceiver() {

    private val bMessages = BroadcastMessages()

    @CallSuper
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "CONNECTIVITY_CHANGE" -> {
                if (!intent.getBooleanExtra("STATUS", false)) {
                    val dialog = AlertDialog.Builder(context)
                    dialog.setTitle(R.string.no_internet_title)
                    dialog.setMessage(R.string.no_internet_message)
                    dialog.setPositiveButton(R.string.retry_message) { _, _ ->
                        Toast.makeText(context, R.string.retrying_toast, Toast.LENGTH_SHORT).show()
                        bMessages.sendConnectivityCheck(context)
                    }
                    dialog.setCancelable(false)
                    dialog.show()
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