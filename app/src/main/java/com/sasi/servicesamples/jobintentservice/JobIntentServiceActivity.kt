package com.sasi.servicesamples.jobintentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sasi.servicesamples.R

class JobIntentServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_intent_service)
    }

    public fun enqueueJob(view: View) {
        Intent(this, SampleJobIntentService::class.java).also {
            SampleJobIntentService.enqueueWork(this, it)
        }
    }

}
