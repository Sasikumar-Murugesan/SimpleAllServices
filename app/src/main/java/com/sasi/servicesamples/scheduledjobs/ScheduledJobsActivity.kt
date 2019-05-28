package com.sasi.servicesamples.scheduledjobs

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.sasi.servicesamples.R

class ScheduledJobsActivity : AppCompatActivity() {
    companion object {
        var TAG = "ScheduledJobs"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheduled_jobs)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public fun scheduleJobs(view: View) {
        val componentName = ComponentName(this, ScheduledJobService::class.java)
        val jobInfo: JobInfo = JobInfo.Builder(ScheduledJobService.JOB_ID, componentName)
            .setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .build()
        val jobScheduler: JobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val jobResult = jobScheduler.schedule(jobInfo)
        if (jobResult == JobScheduler.RESULT_SUCCESS) {
            Log.v(TAG, "Job scheduled");
        } else {
            Log.v(TAG, "Job scheduling failed");
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public fun cancelJobs(view: View) {
        var jobScheduler:JobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(ScheduledJobService.JOB_ID)
        Log.v(TAG, "Job cancelled");
    }
}
