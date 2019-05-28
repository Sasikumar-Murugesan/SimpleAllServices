package com.sasi.servicesamples.backgroundservices

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.sasi.servicesamples.R
import com.sasi.servicesamples.ServiceApp
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.content.Context


class BackgroundServices : Service() {
    companion object {
        var TAG = "BackgroundServices:"
    }

    private val NOTIF_ID = 1
    var runningThread: Thread? = null
    var serviceDestroyed: Boolean = false
    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "onCreate")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.v(TAG, "onBind")

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v(TAG, "onStartCommand")
        serviceDestroyed = false
        if (runningThread == null || !runningThread?.isAlive!!) {
            runningThread = doSynchonized()
        }
        startForeground(NOTIF_ID, showNotification("0"))
        return START_STICKY
    }

    private fun showNotification(notificationText: String): Notification {
        return NotificationCompat.Builder(this, ServiceApp.NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Service Running")
            .setContentText("Loading $notificationText%")
            .setOnlyAlertOnce(true)
            .setOngoing(true)
            .setSmallIcon(com.sasi.servicesamples.R.drawable.ic_launcher_foreground)
            .build()

    }

    private fun updateNotification(notificationText: String) {
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notification = showNotification(notificationText)
        mNotificationManager.notify(NOTIF_ID, notification)
    }

    @Synchronized
    fun doSynchonized(): Thread {
        var thread = Thread(Runnable {
            for (i in 0..50) {
                Log.v(TAG, "$i")
                if (serviceDestroyed) {
                    Thread.currentThread().interrupt();
                    return@Runnable
                } else {
                    updateNotification(i.toString())
                }

                Thread.sleep(1000)
            }
            updateNotification("Loading Completed")
            stopSelf()

        })
        thread.start()

        return thread

    }

    override fun onDestroy() {
        serviceDestroyed = true
        super.onDestroy()

        Log.v(TAG, "Destroyed")
        Toast.makeText(this, "$TAG Destroyed", Toast.LENGTH_SHORT).show();
    }

}
