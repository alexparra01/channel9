package com.test.channel9.presentation.uicomponents.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.test.channel9.R
import com.test.channel9.data.source.remote.workers.FOREGROUND_SERVICE_CHANNEL
import com.test.channel9.data.source.remote.workers.NOTIFICATION_CHANNEL_NAME
import com.test.channel9.data.source.remote.workers.NOTIFICATION_ID
import com.test.channel9.presentation.main.MainActivity
import javax.inject.Inject

class NotificationHandler @Inject constructor(
    private val context: Context
) {

    fun createLatestNewsNotification(text: String){
        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notifyPendingIntent = PendingIntent.getActivity(
            context, 0, notifyIntent, PendingIntent.FLAG_IMMUTABLE
        )
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                FOREGROUND_SERVICE_CHANNEL,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, FOREGROUND_SERVICE_CHANNEL)
            .setContentTitle(context.getString(R.string.notification_latest_title))
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(notifyPendingIntent)
            .setAutoCancel(true)
            .build()
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(NOTIFICATION_ID, builder)
        }
    }

}