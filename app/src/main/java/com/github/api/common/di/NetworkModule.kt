package com.github.api.common.di

import com.github.api.BuildConfig
import com.github.api.GitHub
import com.github.api.common.network.AuthorizationInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.config.Module
import java.util.concurrent.TimeUnit

class NetworkModule : Module() {
    init {
        val client = OkHttpClient.Builder()
            .readTimeout(BuildConfig.API_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }.addInterceptor(AuthorizationInterceptor(BuildConfig.API_TOKEN))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        bind(GitHub::class.java).toInstance(retrofit.create(GitHub::class.java))
    }
}