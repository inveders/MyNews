package com.example.inved.mynews.controller;

import com.example.inved.mynews.searchapi.SearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NyTimesSearchAPI {

    @Headers("APPID: 69b33155fef846e29c9753f95e628397")
    @GET("articlesearch.json")
    Call<SearchResult> getNyTimesSearchAPI(@Query("q") String query, @Query("fq") List<String> filter, @Query("api-key") String apiKey );
}


