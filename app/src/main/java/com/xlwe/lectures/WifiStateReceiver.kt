package com.xlwe.lectures

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class WifiStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        notificationManager.notify(1, notification)
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
    }
}