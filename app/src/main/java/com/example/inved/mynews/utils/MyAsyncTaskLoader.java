package com.example.inved.mynews.utils;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;

import com.example.inved.mynews.topstoriesapi.NewYorkTimesAPI;
import com.example.inved.mynews.topstoriesapi.Result;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.inved.mynews.controller.AbsNyTimesFragment.API_KEY;

public class MyAsyncTaskLoader extends AsyncTaskLoader<List<Result>> {

    private String name;

    //Constructor
    public MyAsyncTaskLoader(Context context,String name){

        super(context);
        this.name = name;

    }


    @Override
    public List<Result> loadInBackground() {

            Call<NewYorkTimesAPI> nyTimesTopStoriesCall = service.getNyTimesAPI(name,API_KEY);

            Response<NewYorkTimesAPI> responseTopStories = null;
            try {
                responseTopStories = nyTimesTopStoriesCall.execute(); //on reste bloqué ici tant que pas fini
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (responseTopStories == null || responseTopStories.body() == null) {
                return null;
            } else

            return (responseTopStories.body()).results;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    //Retrofit
    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();


    private Retrofit retrofit = new Retrofit.Builder() //Par défaut
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/") //API location
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build(); //Par défaut

    private com.example.inved.mynews.controller.NyTimesAPI service = retrofit.create(com.example.inved.mynews.controller.NyTimesAPI.class);



}
