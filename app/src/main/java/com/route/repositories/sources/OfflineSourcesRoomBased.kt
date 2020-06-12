package com.route.repositories.sources

import com.route.model.SourcesItem


/**
 * Created by Mohamed Nabil Mohamed on 5/15/2020.
 * m.nabil.fci2015@gmail.com
 */
class OfflineSourcesRoomBased :NewsSourcesRepo.OfflineDataSource{

   suspend override fun getSources(): List<SourcesItem> {
        return listOf()
    }

    override fun cacheData(data: List<SourcesItem>) {

    }
}