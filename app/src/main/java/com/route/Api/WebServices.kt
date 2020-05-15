package com.route.Api

import com.route.model.NewsResponse
import com.route.model.SourcesResponse
import io.reactivex.Single
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
                       @Query("language") lang:String): Single<SourcesResponse>

    @GET("everything")
    fun getNews(@Query("apiKey") apiKey:String,
                @Query("language") lang:String,
                @Query("sources") sources:String):Single<NewsResponse>

}