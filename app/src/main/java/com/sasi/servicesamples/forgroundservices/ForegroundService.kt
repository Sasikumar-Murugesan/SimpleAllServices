package com.sasi.servicesamples.forgroundservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import android.graphics.Bitmap
import android.R
import android.app.Notification
import androidx.core.app.NotificationCompat
import android.graphics.BitmapFactory
import android.os.Handler
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.sasi.servicesamples.ServiceApp


class ForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationBuilder = NotificationCompat.Builder(this, ServiceApp.NOTIFICATION_CHANNEL_ID)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.ic_btn_speak_now)
            .setPriority(PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(101, notification)
        Toast.makeText(this, "Creating Notification", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable {
            stopSelf()
        }, 1000)

        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }


    /*Explanation

There are 2 important things
to know about the Foreground Service.

The Foreground Service in Android cannot be started without passing a Notification to it!
(Creating a Notification is very very important to let the user know there is a service run by the App)
Service Lifecycle decides if a Service should be running or not. When it comes to overriding
the onStartCommand() method, we are required to provide return modes which are

START_STICKY: This is used for services that are explicitly started and stopped as needed.

START_NOT_STICKY: This is used to let the OS know that there is a need to stay alive only
when there is a command that has to be run. This can mean that the OS can kill the service
if a higher priority Service has to be run.

START_REDELIVER_INTENT: This has the same property as that of the START_NOT_STICKY,
except if the OS kills it, the Service gets fired once again!
This is really important if you are going to be creating a long-running ForegroundService (Might also be a bounded service).
With the understanding from the above, there is a clear picture of how the ForegroundService in android is created.
Notification creation is another important topic that has to be known.
You can refer the Article on Creating Notifications in Android for a clearer understanding of how the Notifications can be created and the ways in which it can be integrated with a ForegroundService also.*/

}

