package com.example.inved.mynews.retrofit;

import com.example.inved.mynews.searchapi.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NyTimesSearchAPI {

    /**SEARCH ARTICLE API*/
    @GET("articlesearch.json")
    Call<SearchResult> getNyTimesSearchAPI(@Query("q") String query, @Query("fq") String filter, @Query("begin_date") String beginDate, @Query("end_date") String endDate, @Query("api-key") String apiKey );
}


