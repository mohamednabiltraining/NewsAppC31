package com.route.newsappc31

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
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
    }
}