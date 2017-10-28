package com.example.android.newsapp.assemble

import com.example.android.newsapp.repository.api.NewsService
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteModule {

    fun assembleService(): NewsService = buildModule()

    private fun provideClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(StethoInterceptor())
                .build()

    }

    private fun buildModule(): NewsService {
        val retrofit = Retrofit.Builder()
                .client(provideClient())
                .baseUrl("http://content.guardianapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
         return retrofit.create(NewsService::class.java)
    }
}
