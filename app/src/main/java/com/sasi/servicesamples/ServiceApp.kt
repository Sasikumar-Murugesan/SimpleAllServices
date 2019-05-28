package com.sasi.servicesamples

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class ServiceApp : Application() {
    companion object {
        val NOTIFICATION_CHANNEL_ID = "my_service_channel"
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

    }


    private fun createNotificationChannel(): String {
        var notificationChannel =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(NOTIFICATION_CHANNEL_ID, "Service Sample", NotificationManager.IMPORTANCE_DEFAULT)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        notificationChannel?.lightColor = Color.BLUE
        notificationChannel?.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val notificationService = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationChannel?.let {
            notificationService.createNotificationChannel(it)
        }
        return NOTIFICATION_CHANNEL_ID
    }
}