package com.route.repositories.sources

import com.route.model.SourcesItem


/**
 * Created by Mohamed Nabil Mohamed on 5/22/2020.
 * m.nabil.fci2015@gmail.com
 */
class OfflineSourcesBasedSharedPreferences ():NewsSourcesRepo.OfflineDataSource{

    override suspend fun getSources(): List<SourcesItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cacheData(data: List<SourcesItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}