package com.route.firebase

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.route.newsappc31.Constants
import com.route.newsappc31.R


/**
 * Created by Mohamed Nabil Mohamed on 6/5/2020.
 * m.nabil.fci2015@gmail.com
 */
class FireBaseMessagingService : FirebaseMessagingService() {

    val TAG = "FB-CM"
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "From: ${message.from}")

        // Check if message contains a data payload.
        message.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: " + message.data)
            val key = message.data.get("key");
            if (key.equals("MESSAGE")) {
                //some send message
                val sender = message.data.get("senderName");
                val message = message.data.get("message");
                //show notification
            } else {
                val image = message.notification?.imageUrl.toString();
                val title = message.notification?.title ?: "";
                val content = message.notification?.body ?: "";

                showNotification(title, content, image)
                //show notification
                //showNotification()
            }

        }

        // Check if message contains a notification payload

    }

    fun showNotification(title: String, content: String, image: String?) {

        if (image == null) {
            var builder = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifcations)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(content)
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager;

            notificationManager.notify(Math.random().toInt(), builder.build());
        }else {
            Glide.with(this)
                .asBitmap()
                .load(image)
                .into(object : CustomTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        //imageView.setImageBitmap(resource)
                        showNotificationWithBitmap(title,content,resource)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })
        }

    }

    private fun showNotificationWithBitmap(title: String, content: String, resource: Bitmap) {
        var builder = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifcations)
            .setContentTitle(title)
            .setContentText(content)
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(resource))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager;

        notificationManager.notify(Math.random().toInt(), builder.build());
    }
}