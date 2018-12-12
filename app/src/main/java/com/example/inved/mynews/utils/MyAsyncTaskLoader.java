package com.example.inved.mynews.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.inved.mynews.controller.NyTimesTopStoriesAPI;
import com.example.inved.mynews.model.topstories.NyTimesTopStories;
import com.example.inved.mynews.model.topstories.Result;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAsyncTaskLoader extends AsyncTaskLoader<List<Result>> {


    /**Constructor*/
    public MyAsyncTaskLoader(Context context){

        super(context);

    }



    @Override
    public List<Result> loadInBackground() {

        Response<NyTimesTopStories> response = null;
        try {
            response = nyTimesTopStoriesCall.execute(); //on reste bloqué ici tant que pas fini
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response==null || response.body()==null){
            return null;
        }
        else
            Log.d("DEBAGO","retrofit");
        return Objects.requireNonNull(response.body()).results ; //sert à rien
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    /**Retrofit A DEPLACER*/
    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    /**Retrofit Top Stories API*/
    private Retrofit retrofit = new Retrofit.Builder() //Par défaut
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/") //API location
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build(); //Par défaut

    private NyTimesTopStoriesAPI service = retrofit.create(NyTimesTopStoriesAPI.class); // nomInterface service = retrofit.create(nomInterface.class)

    private Call<NyTimesTopStories> nyTimesTopStoriesCall = service.getNyTimesTopStories("home","69b33155fef846e29c9753f95e628397");




}
