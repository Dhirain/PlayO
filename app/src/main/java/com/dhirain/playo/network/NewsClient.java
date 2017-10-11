package com.dhirain.playo.network;


import com.dhirain.playo.model.NewsFromNet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dhirain Jain on 11-10-2017.
 */

public interface NewsClient {
    @GET("/api/v1/search")
    Call<NewsFromNet> getAllNews(
            @Query("query") String query
    );
}
