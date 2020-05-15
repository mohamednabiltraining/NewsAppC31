package com.route.repositories.sources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.route.model.SourcesItem
import com.route.model.SourcesResponse
import com.route.newsappc31.NetworkAwareHandler
import io.reactivex.Single


/**
 * Created by Mohamed Nabil Mohamed on 5/15/2020.
 * m.nabil.fci2015@gmail.com
 */
class NewsSourcesRepo (val offlineDataSource: OfflineDataSource,
                       val onlineDataSource: OnlineDataSource,
                       val networkHandler:NetworkAwareHandler){
    val sourcesList = MutableLiveData<List<SourcesItem>>()
    fun getNewsSources():MutableLiveData<List<SourcesItem>>{
        if (networkHandler.isOnline()){
             onlineDataSource.getSources()
                 .subscribe({
                     sourcesList.value = it.sources?: listOf<SourcesItem>()
                     if (it.sources!=null)
                         offlineDataSource.cacheData(it.sources);
                 },{
                     Log.e("error",it.localizedMessage)
                 })
        }else {
           val data = offlineDataSource.getSources();
            sourcesList.value = data
        }
        return sourcesList;
    }

    interface OnlineDataSource{
        fun getSources():Single<SourcesResponse>
    }
    interface OfflineDataSource{
        fun getSources():List<SourcesItem>
        fun cacheData(data: List<SourcesItem>)
    }
}