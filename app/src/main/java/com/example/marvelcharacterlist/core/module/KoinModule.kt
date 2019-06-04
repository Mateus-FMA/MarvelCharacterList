package com.example.marvelcharacterlist.core.module

import com.example.marvelcharacterlist.BuildConfig
import com.example.marvelcharacterlist.core.MarvelAPI
import com.example.marvelcharacterlist.core.MarvelAPIImpl
import com.example.marvelcharacterlist.core.retrofit.MarvelService
import com.example.marvelcharacterlist.core.retrofit.interceptor.APIKeyInterceptor
import com.example.marvelcharacterlist.ui.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val marvelModule = module {
    single { createOkHttpClient() }
    single { createMarvelService(get()) }
    single { MarvelAPIImpl(get()) as MarvelAPI }

    viewModel { MainViewModel(get(), get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(APIKeyInterceptor())
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun createMarvelService(client: OkHttpClient): MarvelService {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MarvelService::class.java)
}