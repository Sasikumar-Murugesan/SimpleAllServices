package com.sasi.servicesamples.forgroundservices

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.sasi.servicesamples.R
import kotlinx.android.synthetic.main.activity_forground_service.*

class ForgroundServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forground_service)
    }

    fun startService(view: View) {
        ContextCompat.startForegroundService(this, Intent(this@ForgroundServiceActivity, ForegroundService::class.java))
    }

    fun stopService(view: View) {
        stopService(Intent(this@ForgroundServiceActivity, ForegroundService::class.java))
    }
}
