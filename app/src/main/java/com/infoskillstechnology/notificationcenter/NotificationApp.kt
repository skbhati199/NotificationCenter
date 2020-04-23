package com.infoskillstechnology.notificationcenter

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class NotificationApp : Application(){

    companion object {
        const val CHANNEL_ID_1 : String = "channel_1"
        const val CHANNEL_ID_2 : String = "channel_2"
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
          val channel1 =  NotificationChannel(CHANNEL_ID_1,"channel 1", NotificationManager.IMPORTANCE_HIGH)
          val channel2 =  NotificationChannel(CHANNEL_ID_2,"channel 2", NotificationManager.IMPORTANCE_LOW)

            channel1.description = "This is channel 1"
            channel2.description = "This is channel 2"


            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel1)
            manager?.createNotificationChannel(channel2)
        }
    }
}