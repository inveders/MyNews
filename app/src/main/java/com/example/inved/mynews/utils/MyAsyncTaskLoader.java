package com.example.inved.mynews.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.inved.mynews.controller.NyTimesAPI;
import com.example.inved.mynews.topstoriesapi.NyTimesTopStories;
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
import static com.example.inved.mynews.controller.AbsNyTimesFragment.sectionName;

public class MyAsyncTaskLoader extends AsyncTaskLoader<List<Result>> {



    /**Constructor*/
    public MyAsyncTaskLoader(Context context){

        super(context);

    }

    @Override
    public List<Result> loadInBackground() {

            Response<NyTimesTopStories> responseTopStories = null;
            try {
                responseTopStories = nyTimesTopStoriesCall.execute(); //on reste bloqué ici tant que pas fini
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (responseTopStories == null || responseTopStories.body() == null) {
                return null;
            } else
                Log.d("DEBAGO", "retrofit" + sectionName);
            return (responseTopStories.body()).resultsTopStories; //the Objects.requiresNonNull is not necessary
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

    private NyTimesAPI service = retrofit.create(NyTimesAPI.class); // nomInterface service = retrofit.create(nomInterface.class)
    private Call<NyTimesTopStories> nyTimesTopStoriesCall = service.getNyTimesTopStories(sectionName,API_KEY);


}
