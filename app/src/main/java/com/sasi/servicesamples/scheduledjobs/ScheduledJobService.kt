package com.sasi.servicesamples.scheduledjobs

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ScheduledJobService : JobService() {
    var TAG = "ScheduledJobService"

    companion object {
        var JOB_ID = 1256
    }

    var isJobCancelled: Boolean = false
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.v(TAG, "onStartJob")
        doBackgroundTask(params)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.v(TAG, "Job cancelled before completion")
        isJobCancelled = true
        return true
    }

    private fun doBackgroundTask(params: JobParameters?) {
        Thread(Runnable {
            for (i in 0..10) {
                Log.v(TAG, "$i")
                if (isJobCancelled) {
                    return@Runnable;
                }
                SystemClock.sleep(1000)
            }
            Log.v(TAG, "Job Finished!")
            jobFinished(params, false)
        }).start()

    }
}