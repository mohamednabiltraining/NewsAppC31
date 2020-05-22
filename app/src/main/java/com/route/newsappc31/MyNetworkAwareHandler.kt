package com.route.newsappc31

import android.content.Context


/**
 * Created by Mohamed Nabil Mohamed on 5/22/2020.
 * m.nabil.fci2015@gmail.com
 */

class MyNetworkAwareHandler(val context: Context) :NetworkAwareHandler{

    override fun isOnline(): Boolean {


        return true;
    }

}