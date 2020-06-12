package com.route.repositories.sources

import com.route.Api.ApiManager
import com.route.model.SourcesItem
import com.route.newsappc31.Constants


/**
 * Created by Mohamed Nabil Mohamed on 5/15/2020.
 * m.nabil.fci2015@gmail.com
 */
class OnlineSourcesBasedRetroFit :NewsSourcesRepo.OnlineDataSource{
    override suspend fun getSources(): List<SourcesItem> {
        val response =  ApiManager.getApis()
            .getNewsSources(Constants.apiKey,"en")
        return response.sources.orEmpty()

    }
}