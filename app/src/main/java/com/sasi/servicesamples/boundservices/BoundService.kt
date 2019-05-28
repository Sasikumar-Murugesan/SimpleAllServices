package com.sasi.servicesamples.boundservices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

class BoundService : Service() {
    companion object {
        public var TAG = "BoundService:"
    }

    // Binder given to clients
    private val binder = LocalBinder()
    /** method for clients  */
    val randomNumber: Int get() = Random.nextInt(100)

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.v(TAG, "onBind")

        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v(TAG, "onStartCommand")
        return START_STICKY
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.v(TAG, "onUnbind")
        return super.onUnbind(intent)

    }

    override fun onRebind(intent: Intent?) {
        Log.v(TAG, "onRebind")
        super.onRebind(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): BoundService = this@BoundService
    }


}