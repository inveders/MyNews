package com.example.inved.mynews.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.inved.mynews.controller.NyTimesMostPopularAPI;
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

public class MyAsyncTaskLoaderMostPopular extends AsyncTaskLoader<List<Result>> {

    String name;
    int period;

    /**
     * Constructor
     */
    public MyAsyncTaskLoaderMostPopular(Context context, String name, int period) {

        super(context);
        this.name = name;
        this.period = period;
    }

    @Override
    public List<Result> loadInBackground() {

       Call<NewYorkTimesAPI> nyTimesMostPopularCall = service.getNyTimesMostPopular(name, period, API_KEY);

        /**1st retrofit call*/
        Response<NewYorkTimesAPI> responseMostPopular = null;
        try {
            Log.d("DEBAGA", "before retrofit Most Popular");
            responseMostPopular = nyTimesMostPopularCall.execute(); //on reste bloqué ici tant que pas fini
            Log.d("DEBAGA", "retrofit Most Popular");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseMostPopular == null || responseMostPopular.body() == null) {
            return null;
        } else


            return (responseMostPopular.body()).results; //the Objects.requiresNonNull is not necessary

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    /**
     * Retrofit A DEPLACER
     */
    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    /**
     * Retrofit Top Stories API
     */
    private Retrofit retrofit = new Retrofit.Builder() //Par défaut
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/") //API location
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build(); //Par défaut

    private NyTimesMostPopularAPI service = retrofit.create(NyTimesMostPopularAPI.class); // nomInterface service = retrofit.create(nomInterface.class)


}

