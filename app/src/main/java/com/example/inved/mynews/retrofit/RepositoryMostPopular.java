package com.example.inved.mynews.retrofit;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.inved.mynews.topstoriesapi.NewYorkTimesAPI;
import com.example.inved.mynews.topstoriesapi.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.inved.mynews.controller.AbsNyTimesFragment.API_KEY;

public class RepositoryMostPopular {

    private ArrayList<Result> results = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private int period = 7;
    private Application application;

    public RepositoryMostPopular(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Result>> getMutableLiveData(String name) {

        NyTimesMostPopularAPI nyTimesMostPopularAPI = RetrofitServiceMostPopular.getApiServiceMostPopular();

        Call<NewYorkTimesAPI> call = nyTimesMostPopularAPI.getNyTimesMostPopular(name,period, API_KEY);

        call.enqueue(new Callback<NewYorkTimesAPI>() {
            @Override
            public void onResponse(Call<NewYorkTimesAPI> call, Response<NewYorkTimesAPI> response) {
                NewYorkTimesAPI newYorkTimesAPI = response.body();
                if (newYorkTimesAPI != null || newYorkTimesAPI.results != null) {
                    results = (ArrayList<Result>) newYorkTimesAPI.results;
                    mutableLiveData.setValue(results);
                }
            }

            @Override
            public void onFailure(Call<NewYorkTimesAPI> call, Throwable t) {

            }
        });


        return mutableLiveData;


    }
}
