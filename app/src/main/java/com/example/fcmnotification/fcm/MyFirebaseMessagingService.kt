package com.example.fcmnotification.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.fcmnotification.MainActivity
import com.example.fcmnotification.MyApplication
import com.example.fcmnotification.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
//        val notification = remoteMessage.notification ?: return
//        val title = notification.title
//        val message = notification.body
        val stringMap = remoteMessage.data
        val title = stringMap.get("key_1")
        val body = stringMap.get("key_2")
        sendNotification(title , body)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    private fun sendNotification(title: String?, message: String?) {
        val intent = Intent(this , MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationBuilder = NotificationCompat.Builder(this,MyApplication.CHANEL_ID)
                                  .setContentTitle(title)
                                  .setContentText(message)
                                  .setSmallIcon(R.drawable.ic_notification)
                                  .setContentIntent(pendingIntent)
        val notification = notificationBuilder.build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        with(notificationManager) { notify(1, notification) }
    }

    companion object {
        const val TAG = "MyFirebaseMessagingService"
    }

}