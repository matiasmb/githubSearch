package com.example.githubsearch

import android.app.Application
import com.example.githubsearch.data.model.GithubRepo
import com.example.githubsearch.data.networking.ItemsApiClient
import com.example.githubsearch.data.networking.ItemsApiService
import com.example.githubsearch.data.networking.ItemsApiServiceImpl
import com.example.githubsearch.domain.DomainInteractor
import com.example.githubsearch.domain.DomainInteractorImpl
import com.example.githubsearch.presentation.viewmodel.ItemsViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application : Application() {

    private val appModule = module {
        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(ItemsApiClient::class.java)
        }
        viewModel { ItemsViewModel(get()) }
        factory<DomainInteractor> { DomainInteractorImpl(get()) }
        factory<ItemsApiService<List<GithubRepo>>> { ItemsApiServiceImpl(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@Application)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(appModule)
        }
    }
}