package com.route.Api

import com.route.model.NewsResponse
import com.route.model.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Mohamed Nabil Mohamed on 3/6/2020.
 * m.nabil.fci2015@gmail.com
 */
interface WebServices {
    //sources
    @GET("sources")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("language") lang: String
    ): SourcesResponse

    @GET("everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("language") lang: String,
        @Query("sources") sources: String
    ): NewsResponse

}