package com.sasi.servicesamples.backgroundservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.sasi.servicesamples.R

class BackgroundServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_service)
    }

    fun startService(view: View) {
           ContextCompat.startForegroundService(this,Intent(this@BackgroundServicesActivity, BackgroundServices::class.java))
            //startService(Intent(this@BackgroundServicesActivity, BackgroundServices::class.java))
    }

    fun stopService(view: View) {
        stopService(Intent(this@BackgroundServicesActivity, BackgroundServices::class.java))
    }
}
