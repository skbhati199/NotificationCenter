package com.infoskillstechnology.notificationcenter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"onReceive " + intent.getStringExtra("message"), Toast.LENGTH_LONG).show()
    }
}
