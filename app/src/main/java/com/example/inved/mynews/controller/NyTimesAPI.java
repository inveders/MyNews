package com.example.inved.mynews.controller;

import com.example.inved.mynews.mostpopularapi.NyTimesMostPopular;
import com.example.inved.mynews.topstoriesapi.NyTimesTopStories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NyTimesAPI {
    /**TOP STORIES API*/
    @Headers("APPID:  69b33155fef846e29c9753f95e628397")
    @GET("{section}.json")
    Call<NyTimesTopStories> getNyTimesTopStories(@Path("section") String section, @Query("api-key") String key );



}
