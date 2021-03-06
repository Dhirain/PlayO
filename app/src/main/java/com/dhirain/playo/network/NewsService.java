package com.dhirain.playo.network;


import com.dhirain.playo.NewsGoApp;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dhirain Jain on 11-10-2017.
 */

public class NewsService {

    public static final String API_BASE_URL = "http://hn.algolia.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static NewsClient builder;

    public static NewsClient instance() {
        return builder;
    }

    static {
        //setup cache
        httpClient.addNetworkInterceptor(new CacheInterceptor());
        httpClient.cache(setCacheSize(10 * 1024 * 1024));// 10 MB

        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build().create(NewsClient.class);
    }

    private static Cache setCacheSize(int cacheSize) {
        File httpCacheDirectory = new File(NewsGoApp.singleton().getContext().getCacheDir(), "responses");
        return new Cache(httpCacheDirectory, cacheSize);
    }

}
