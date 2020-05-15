package com.route.Api

import com.route.data.NewsResponse
import com.route.data.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Mohamed Nabil Mohamed on 3/6/2020.
 * m.nabil.fci2015@gmail.com
 */
interface WebServices {
    //sources
    @GET("sources")
    fun getNewsSources(@Query("apiKey") apiKey:String,
                       @Query("language") lang:String): Call<SourcesResponse>

    @GET("everything")
    fun getNews(@Query("apiKey") apiKey:String,
                @Query("language") lang:String,
                @Query("sources") sources:String):Call<NewsResponse>

}