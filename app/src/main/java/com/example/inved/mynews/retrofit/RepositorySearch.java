package com.example.inved.mynews.retrofit;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.inved.mynews.searchapi.Doc;
import com.example.inved.mynews.searchapi.SearchResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.inved.mynews.controller.AbsNyTimesFragment.API_KEY;

public class RepositorySearch {



    private ArrayList<Doc> results = new ArrayList<>();
    private MutableLiveData<ArrayList<Doc>> mutableLiveData = new MutableLiveData<>();

    public RepositorySearch() {
    }

    public MutableLiveData<ArrayList<Doc>> getMutableLiveData(String query,String filter,String beginDate,String endDate) {

        NyTimesSearchAPI nyTimesSearchAPI = RetrofitServiceSearch.getApiServiceSearch();

        Call<SearchResult> call = nyTimesSearchAPI.getNyTimesSearchAPI(query, filter, beginDate, endDate, API_KEY);

        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(@NonNull Call<SearchResult> call,@NonNull Response<SearchResult> response) {
                SearchResult searchResult = response.body();

                if(searchResult!=null && searchResult.response!=null && searchResult.response.docs!=null){

                    results = searchResult.response.docs;
                    mutableLiveData.setValue(results);

                }


            }

            @Override
            public void onFailure(@NonNull Call<SearchResult> call,@NonNull Throwable t) {

            }
        });

        return mutableLiveData;

    }
}
