package com.route.repositories.sources

import com.route.Api.ApiManager
import com.route.model.SourcesResponse
import com.route.newsappc31.Constants
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Mohamed Nabil Mohamed on 5/15/2020.
 * m.nabil.fci2015@gmail.com
 */
class OnlineSourcesBasedRetroFit :NewsSourcesRepo.OnlineDataSource{
    override fun getSources(): Single<SourcesResponse> {
        return ApiManager.getApis()
            .getNewsSources(Constants.apiKey,"en")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
}