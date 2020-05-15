package com.route.newsappc31.UI.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.Api.ApiManager
import com.route.data.ArticlesItem
import com.route.data.NewsResponse
import com.route.data.SourcesItem
import com.route.data.SourcesResponse
import com.route.newsappc31.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Mohamed Nabil Mohamed on 3/27/2020.
 * m.nabil.fci2015@gmail.com
 */
class HomeViewModel : ViewModel(){

    val sourcesLiveData = MutableLiveData< List<SourcesItem?>>()
    val showLoadingLiveData  = MutableLiveData<Boolean>()
    val messageStringLiveData = MutableLiveData<String>()
    val newsList =MutableLiveData<List<ArticlesItem?>>()

    fun getNewsSources() {
      //  showLoadingDialog(getString(R.string.loading))
        showLoadingLiveData.value =true

        ApiManager.getApis()
            .getNewsSources(Constants.apiKey,"en")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    showLoadingLiveData.value = false
                    //         hideLoadingDialog()
               //     setUpTabLayout(response.body()?.sources)
                    sourcesLiveData.value = response.body()?.sources
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    showLoadingLiveData.value = false
                    //   hideLoadingDialog()
//                    showMessage("",t.localizedMessage,null,null, null,null,true)
                    messageStringLiveData.value = t.localizedMessage
                }
            })
    }

    fun getNewsBySourceId(sourceID:String){
        //showLoadingDialog(getString(R.string.loading))
        showLoadingLiveData.value = true
        ApiManager.getApis().getNews(
            Constants.apiKey,"en",
            sourceID)
            .enqueue(object :Callback<NewsResponse>{
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    showLoadingLiveData.value=false
//                    hideLoadingDialog()
                    //send id to recycler view adapter
                     //response.body().articles
                    newsList.value = response.body()?.articles
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                  //  hideLoadingDialog()
                   showLoadingLiveData.value=false
//                    showMessage("",t.localizedMessage,null,null, null,null,true)
                    messageStringLiveData.value = t.localizedMessage

                }
            })
    }

}