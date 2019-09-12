package com.example.inved.mynews.retrofit;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.inved.mynews.topstoriesapi.NewYorkTimesAPI;
import com.example.inved.mynews.topstoriesapi.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.inved.mynews.controller.fragments.AbsNyTimesFragment.API_KEY;

public class RepositoryMostPopular {

    private ArrayList<Result> results = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();

    public RepositoryMostPopular() {
    }

    public MutableLiveData<List<Result>> getMutableLiveData(String name) {

        NyTimesMostPopularAPI nyTimesMostPopularAPI = RetrofitServiceMostPopular.getApiServiceMostPopular();

        int period = 7;
        Call<NewYorkTimesAPI> call = nyTimesMostPopularAPI.getNyTimesMostPopular(name, period, API_KEY);

        call.enqueue(new Callback<NewYorkTimesAPI>() {
            @Override
            public void onResponse(@NonNull Call<NewYorkTimesAPI> call,@NonNull Response<NewYorkTimesAPI> response) {
                NewYorkTimesAPI newYorkTimesAPI = response.body();
                if(newYorkTimesAPI!=null){
                    if (newYorkTimesAPI.results != null) {
                        results = (ArrayList<Result>) newYorkTimesAPI.results;
                        mutableLiveData.setValue(results);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<NewYorkTimesAPI> call,@NonNull Throwable t) {

            }
        });


        return mutableLiveData;


    }
}
