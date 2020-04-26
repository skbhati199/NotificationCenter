package com.infoskillstechnology.notificationcenter

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.transition.Transition
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = NotificationManagerCompat.from(this)
    }

    fun sendOnChannel1(view: View) {

//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.notification)

//        Glide.with(this).load("http://goo.gl/gEgYUd")



//        MyImageDownloadTask(this,ed_title.text.toString(),
//            ed_description.text.toString(),
//            notificationManager).execute()

        getBitmapAsyncAndDoWork("https://thumbs.dreamstime.com/b/purple-flower-2212075.jpg")

    }
    fun sendOnChannel2(view: View) {

        val notification = NotificationCompat.Builder(this, NotificationApp.CHANNEL_ID_2)
            .setSmallIcon(R.drawable.ic_notifications_active_green)
            .setContentTitle(ed_title.text.toString())
            .setContentText(ed_description.text.toString())
            .setStyle(NotificationCompat.InboxStyle()
                .addLine("Line 1")
                .addLine("Line 2")
                .addLine("Line 3")
                .addLine("Line 4")
                .addLine("Line 5")
                .addLine("Line 6")
                .addLine("Line 7")
                .addLine("Line 8")
                .setBigContentTitle("Big Content Title")
                .setSummaryText("Summary title")
                )
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()


        notificationManager.notify(2, notification)

    }


    private fun getBitmapAsyncAndDoWork(imageUrl: String) {
        val context = this
        Glide.with(applicationContext)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                ) {


                    val activityIntent = Intent(context, MainActivity::class.java)
                    val pendingActivityIntent = PendingIntent.getActivity(context,
                        0,activityIntent,0)

                    val broadcastReceiver = Intent(context, MyNotificationReceiver::class.java)
                    broadcastReceiver.putExtra("message", "Desc  ")

                    val pendingAction = PendingIntent.getBroadcast(context,
                        0,broadcastReceiver, PendingIntent.FLAG_UPDATE_CURRENT)

                    val notification = NotificationCompat.Builder(context, NotificationApp.CHANNEL_ID_1)
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentTitle(title)
                        .setContentText("ddd")
                        .setLargeIcon(resource)
                        .setStyle(NotificationCompat.BigTextStyle()
                            .bigText("Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello ")
                            .setBigContentTitle("Big Content Title")
                            .setSummaryText("Summary Text"))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setContentIntent(pendingActivityIntent)
                        .setAutoCancel(true)
                        .setOnlyAlertOnce(true)
                        .addAction(R.mipmap.ic_launcher,"Click",pendingAction)
                        .build()


                    notificationManager.notify(1, notification)

                     }

            })
    }

    class MyImageDownloadTask(val context: Context, val title:String,
                              val desc:String,
                              val notificationManager: NotificationManagerCompat)
        : AsyncTask<Unit,Bitmap,Bitmap>() {
        override fun doInBackground(vararg params: Unit?): Bitmap {
            val bitmap: Bitmap = Glide.with(context)
                .asBitmap()
                .load("https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300")
                .into(100, 100)
                .get()

            return bitmap
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            super.onPostExecute(bitmap)
            val activityIntent = Intent(context, MainActivity::class.java)
            val pendingActivityIntent = PendingIntent.getActivity(context,
                0,activityIntent,0)

            val broadcastReceiver = Intent(context, MyNotificationReceiver::class.java)
            broadcastReceiver.putExtra("message", desc)

            val pendingAction = PendingIntent.getBroadcast(context,
                0,broadcastReceiver, PendingIntent.FLAG_UPDATE_CURRENT)

            val notification = NotificationCompat.Builder(context, NotificationApp.CHANNEL_ID_1)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle(title)
                .setContentText(desc)
                .setLargeIcon(bitmap)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello ")
                    .setBigContentTitle("Big Content Title")
                    .setSummaryText("Summary Text"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingActivityIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher,"Click",pendingAction)
                .build()


            notificationManager.notify(1, notification)

        }

    }
}
