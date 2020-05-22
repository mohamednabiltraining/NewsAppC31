package com.route.newsappc31.di

import com.route.newsappc31.MyNetworkAwareHandler
import com.route.newsappc31.NetworkAwareHandler
import com.route.newsappc31.UI.home.HomeViewModel
import com.route.repositories.sources.NewsSourcesRepo
import com.route.repositories.sources.OfflineSourcesRoomBased
import com.route.repositories.sources.OnlineSourcesBasedRetroFit
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Mohamed Nabil Mohamed on 5/22/2020.
 * m.nabil.fci2015@gmail.com
 */

val dataSourcesModule = module {
    factory <NewsSourcesRepo.OfflineDataSource>{ OfflineSourcesRoomBased() }
    factory<NewsSourcesRepo.OnlineDataSource> { OnlineSourcesBasedRetroFit() }

}
val networkModule = module {
    single<NetworkAwareHandler>{MyNetworkAwareHandler(androidContext())}
}
val repositoriesModule = module {
    single<NewsSourcesRepo> { NewsSourcesRepo(get(),get(),get())}
}

val viewModelsModule = module {
    viewModel{ HomeViewModel(get()) }
}
