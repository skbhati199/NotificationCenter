package com.infoskillstechnology.notificationcenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = NotificationManagerCompat.from(this)
    }

    fun sendOnChannel1(view: View) {

        val notification = NotificationCompat.Builder(this, NotificationApp.CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
            .setContentTitle(ed_title.text.toString())
            .setContentText(ed_description.text.toString())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .build()


        notificationManager.notify(1, notification)

    }
    fun sendOnChannel2(view: View) {

        val notification = NotificationCompat.Builder(this, NotificationApp.CHANNEL_ID_2)
            .setSmallIcon(R.drawable.ic_notifications_active_green)
            .setContentTitle(ed_title.text.toString())
            .setContentText(ed_description.text.toString())
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()


        notificationManager.notify(2, notification)

    }
}
