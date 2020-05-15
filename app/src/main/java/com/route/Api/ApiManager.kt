package com.route.Api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Mohamed Nabil Mohamed on 3/6/2020.
 * m.nabil.fci2015@gmail.com
 */

class ApiManager {
    companion object{

        private var retrofit:Retrofit
        init {
            retrofit = Retrofit.Builder()
                .baseUrl("http://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        fun getApis():WebServices{
          return retrofit.create(WebServices::class.java);
        }

    }
}