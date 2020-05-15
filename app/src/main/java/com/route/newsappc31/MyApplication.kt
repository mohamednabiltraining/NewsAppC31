package com.route.newsappc31

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication


/**
 * Created by Mohamed Nabil Mohamed on 5/15/2020.
 * m.nabil.fci2015@gmail.com
 */
class MyApplication() : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this);
    }
}