package com.example.fcmnotification.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.fcmnotification.MainActivity
import com.example.fcmnotification.MyApplication
import com.example.fcmnotification.R
import com.example.fcmnotification.ResultActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

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
//        val intent = Intent(this , MainActivity::class.java)
        val resultIntent = Intent(this, ResultActivity::class.java)
// Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the , which intentinflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(getNotificationId(), PendingIntent.FLAG_UPDATE_CURRENT)
        }
      //  val pendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationBuilder = NotificationCompat.Builder(this,MyApplication.CHANEL_ID)
                                  .setContentTitle(title)
                                  .setContentText(message)
                                  .setSmallIcon(R.drawable.ic_notification)
                                  .setContentIntent(resultPendingIntent)
        val notification = notificationBuilder.build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        with(notificationManager) { notify(getNotificationId(), notification) }
    }

    private fun getNotificationId(): Int = Date().time.toInt()

    companion object {
        const val TAG = "MyFirebaseMessagingService"
    }

}