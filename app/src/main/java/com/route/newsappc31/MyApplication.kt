package com.route.newsappc31

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.route.newsappc31.di.dataSourcesModule
import com.route.newsappc31.di.networkModule
import com.route.newsappc31.di.repositoriesModule
import com.route.newsappc31.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Mohamed Nabil Mohamed on 5/15/2020.
 * m.nabil.fci2015@gmail.com
 */
class MyApplication : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this);

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(
                networkModule,
                dataSourcesModule,
                repositoriesModule,
                viewModelsModule
            ))
        }
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FB-token", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Log.d("FB-token", token)
                // 99% token is needed when your backend needs to send specific notification
                //to this user only
                val sharedPreferences = getSharedPreferences("default", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString(Constants.FB_TOKEN,token)
                    .apply();

                // Log and toast
                //    val msg = getString(R.string.msg_token_fmt, token)
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
        createNotificationChannel()

    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.news_channelId)
            val descriptionText = getString(R.string.news_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Constants.CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}