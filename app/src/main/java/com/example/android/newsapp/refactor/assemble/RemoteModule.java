package com.example.android.newsapp.refactor.assemble;

import com.example.android.newsapp.refactor.repository.remote.api.NewsService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteModule {

    public static RemoteModule instance;

    private NewsService service;

    private RemoteModule() {
        buildModule();
    }

    private static RemoteModule getInstance() {
        if (instance == null) {
            instance = new RemoteModule();
        }
        return instance;
    }

    public static NewsService getService() {
        if (instance == null) {
            instance = getInstance();
        }
        return instance.service;
    }

    private OkHttpClient provideClient() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(new StethoInterceptor())
                .build();

    }


    private void buildModule() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(provideClient())
                .baseUrl("http://content.guardianapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NewsService.class);
    }
}
