package com.example.inved.mynews.controller;

import com.example.inved.mynews.topstoriesapi.NewYorkTimesAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NyTimesMostPopularAPI {

    /**MOST POPULAR API*/
    @Headers("APPID: 69b33155fef846e29c9753f95e628397")
    @GET("mostemailed/{section}/{time-period}.json")
    Call<NewYorkTimesAPI> getNyTimesMostPopular(@Path("section") String section, @Path("time-period") int period, @Query("api-key") String key );
}
