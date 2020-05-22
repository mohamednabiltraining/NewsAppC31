package com.route.newsappc31.di

import com.route.newsappc31.MyNetworkAwareHandler
import com.route.repositories.sources.NewsSourcesRepo
import com.route.repositories.sources.OfflineSourcesRoomBased
import com.route.repositories.sources.OnlineSourcesBasedRetroFit
import org.koin.dsl.module


/**
 * Created by Mohamed Nabil Mohamed on 5/22/2020.
 * m.nabil.fci2015@gmail.com
 */
val dataSourcesModule = module {
    factory{ OfflineSourcesRoomBased() }
    factory { OnlineSourcesBasedRetroFit()}

}
val networkModule = module {
    single{MyNetworkAwareHandler(get())}
}
val repositoriesModule = module {
    single { NewsSourcesRepo(get(),get(),get())}
}

val viewModelsModule = module {
    //viewModel{HomeViewModel(get())}
}
