package com.route.repositories.sources

import com.route.model.SourcesItem
import com.route.newsappc31.NetworkAwareHandler


/**
 * Created by Mohamed Nabil Mohamed on 5/15/2020.
 * m.nabil.fci2015@gmail.com
 */
class NewsSourcesRepo (val offlineDataSource: OfflineDataSource,
                       val onlineDataSource: OnlineDataSource,
                       val networkHandler:NetworkAwareHandler){
    suspend fun getNewsSources():List<SourcesItem>{


                if (networkHandler.isOnline()) {
                    val result = onlineDataSource.getSources();

                    if (result != null)
                        offlineDataSource.cacheData(result);

                    return result.orEmpty()

                } else {
                    return offlineDataSource.getSources()
                }

    }

    interface OnlineDataSource{
        suspend fun getSources():List<SourcesItem>
    }
    interface OfflineDataSource{
        suspend fun getSources():List<SourcesItem>
        fun cacheData(data: List<SourcesItem>)
    }
}