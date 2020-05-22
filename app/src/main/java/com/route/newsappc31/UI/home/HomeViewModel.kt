package com.route.newsappc31.UI.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.Api.ApiManager
import com.route.model.ArticlesItem
import com.route.model.SourcesItem
import com.route.newsappc31.Constants
import com.route.repositories.sources.NewsSourcesRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent


/**
 * Created by Mohamed Nabil Mohamed on 3/27/2020.
 * m.nabil.fci2015@gmail.com
 */
class HomeViewModel(val newsSourcesRepo:NewsSourcesRepo) : ViewModel( ),KoinComponent{


    lateinit var sourcesLiveData : MutableLiveData< List<SourcesItem>>

    val showLoadingLiveData  = MutableLiveData<Boolean>()
    val messageStringLiveData = MutableLiveData<String>()
    val newsList =MutableLiveData<List<ArticlesItem?>>()
    val compositeDisposable = CompositeDisposable();
   // val newsSourcesRepo:NewsSourcesRepo by inject()

    init {

        sourcesLiveData = newsSourcesRepo.sourcesList
        newsSourcesRepo.getNewsSources()
    }

    fun getNewsSources() {
        showLoadingLiveData.value =true
        newsSourcesRepo.getNewsSources()
    }

    val errorHandler = Consumer<Throwable> {
        showLoadingLiveData.value=false
        messageStringLiveData.value = it.localizedMessage

    } ;
    fun getNewsBySourceId(sourceID:String){
        //showLoadingDialog(getString(R.string.loading))
        showLoadingLiveData.value = true
        val disposable =ApiManager.getApis().getNews(
            Constants.apiKey,"en",
            sourceID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                showLoadingLiveData.value=false
//                    hideLoadingDialog()
                //send id to recycler view adapter
                //response.body().articles
                newsList.value = it.articles

            }, this.errorHandler)
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}