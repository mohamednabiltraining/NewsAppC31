package com.route.newsappc31.UI.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.Api.ApiManager
import com.route.model.ArticlesItem
import com.route.model.SourcesItem
import com.route.newsappc31.Constants
import com.route.repositories.sources.NewsSourcesRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


/**
 * Created by Mohamed Nabil Mohamed on 3/27/2020.
 * m.nabil.fci2015@gmail.com
 */
class HomeViewModel(val newsSourcesRepo: NewsSourcesRepo) : ViewModel(), KoinComponent {


    val sourcesLiveData = MutableLiveData<List<SourcesItem>>()

    val showLoadingLiveData = MutableLiveData<Boolean>()
    val messageStringLiveData = MutableLiveData<String>()
    val newsList = MutableLiveData<List<ArticlesItem?>>()
    val compositeDisposable = CompositeDisposable();
    // val newsSourcesRepo:NewsSourcesRepo by inject()\


    fun getNewsSources() {

        showLoadingLiveData.value = true
        viewModelScope.launch {
            try {
                val result = newsSourcesRepo.getNewsSources()
                sourcesLiveData.postValue(result)

            } catch (t: Throwable) {
                showLoadingLiveData.value = false
                messageStringLiveData.value = t.localizedMessage

            }

        }
    }

    val errorHandler = Consumer<Throwable> {
        showLoadingLiveData.value = false
        messageStringLiveData.value = it.localizedMessage

    };

    fun getNewsBySourceId(sourceID: String) {
        //showLoadingDialog(getString(R.string.loading))
        showLoadingLiveData.value = true
        viewModelScope.launch {
            try {
                val response = ApiManager.getApis().getNews(
                    Constants.apiKey, "en",
                    sourceID
                );
                showLoadingLiveData.value = false
                newsList.postValue(response.articles);

            } catch (t: Throwable) {
                showLoadingLiveData.value = false
                messageStringLiveData.value = t.localizedMessage

            }
        }

    }

    override fun onCleared() {
        super.onCleared()
    }
}