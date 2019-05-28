package com.sasi.servicesamples.intentServices

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.sasi.servicesamples.R
import kotlinx.android.synthetic.main.activity_intent_service.*

class IntentServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)
    }

    public fun startIntentService(view: View) {
        Intent(this@IntentServiceActivity, IntentServices::class.java).also {
            it.putExtra("size", edit_text_input.text.toString().toInt())
            ContextCompat.startForegroundService(this, it)
        }
    }
}
