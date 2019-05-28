package com.sasi.servicesamples.intentServices

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import com.sasi.servicesamples.ServiceApp

class IntentServices : IntentService("IntentService") {
    companion object {
        var TAG = "IntentServices"
    }

    init {
        setIntentRedelivery(true)
    }

    var wakeLock: PowerManager.WakeLock? = null
    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "onCreate")
        var powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "IntentService:App")
        wakeLock?.acquire()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, createNotification(0))
        }
    }

    private fun createNotification(loading: Int): Notification? {
        return NotificationCompat.Builder(this, ServiceApp.NOTIFICATION_CHANNEL_ID)
            .setContentText("Intent Service Running $loading")
            .setContentTitle("Intent Service Sample")
            .build()
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.v(TAG, "onHandleIntent Started")
        var mSize = intent?.getIntExtra("size", 0)
        doBackgroundWork(mSize)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
        wakeLock?.release()
        Log.v(TAG, "Wakelock released");
    }

    private fun doBackgroundWork(mSize: Int?) {
        mSize?.let {
            for (i in 0..it) {
                Log.v(TAG, "$i")
                updateNotification(i)
                SystemClock.sleep(1000)
            }

        }

    }

    public fun updateNotification(count: Int) {
        var mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, createNotification(count))
    }
}
