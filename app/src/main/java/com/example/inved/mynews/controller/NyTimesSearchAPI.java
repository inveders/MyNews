package com.example.inved.mynews.controller;

import com.example.inved.mynews.searchapi.SearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NyTimesSearchAPI {

    @GET("articlesearch.json")
    Call<SearchResult> getNyTimesSearchAPI(@Query("q") String query, @Query("fq") String filter, @Query("begin_date") String beginDate, @Query("end_date") String endDate, @Query("api-key") String apiKey );
}


