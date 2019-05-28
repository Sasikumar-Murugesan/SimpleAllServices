package com.sasi.servicesamples.jobintentservice

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.core.content.ContextCompat
import com.sasi.servicesamples.scheduledjobs.ScheduledJobService
import com.sasi.servicesamples.scheduledjobs.ScheduledJobService.Companion.JOB_ID

class SampleJobIntentService : JobIntentService() {
    companion object {
        var TAG = "SampleJobIntentService"
        /**
         * Unique job ID for this service.
         */
        val JOB_ID = 1000

        /**
         * Convenience method for enqueuing work in to this service.
         */
        internal fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, SampleJobIntentService::class.java, ScheduledJobService.JOB_ID, work)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "onCreate")

    }

    override fun onHandleWork(intent: Intent) {
        Log.v(TAG, "onHandleWork")
        // We have received work to do.  The system or framework is already
        // holding a wake lock for us at this point, so we can just go.
        for (i in 0..5) {
            Log.i(
                TAG, "Running service " + (i + 1)
                        + "/5 @ " + SystemClock.elapsedRealtime()
            )
            if (isStopped) {
                return
            }
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
            }
        }
        Log.v(TAG, "Completed service @ " + SystemClock.elapsedRealtime())

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
    }

    override fun onStopCurrentWork(): Boolean {
        Log.v(TAG, "onStopCurrentWork")
        return super.onStopCurrentWork()
    }

}