package com.sasi.servicesamples.boundservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.sasi.servicesamples.R
import kotlinx.android.synthetic.main.activity_bound_service.*

class BoundServiceActivity : AppCompatActivity() {
    lateinit var mServices: BoundService
    var isBound: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
    }

    public fun getRandomNumber(view: View) {
        if (isBound) {
            text_random.setText(mServices?.randomNumber.toString())
        }
    }

    public fun bindService(view: View) {
        // Bind to BindService
        Intent(this, BoundService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    public fun unBindService(view: View) {
        unbindService(connection)
        isBound = false
    }

    var connection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as BoundService.LocalBinder
            isBound = true
            mServices = binder.getService()
        }

    }
}
