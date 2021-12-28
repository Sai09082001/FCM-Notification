package com.example.fcmnotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplication : Application() {
    companion object{
        val CHANEL_ID = "CHANEL_ID"
    }

    override fun onCreate() {
        super.onCreate()
        createChanelNotification()
    }

    private fun createChanelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) run {
            val notificationChannel =
                NotificationChannel(CHANEL_ID, "Push Notification", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}