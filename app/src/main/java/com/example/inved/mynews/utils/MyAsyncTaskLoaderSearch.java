package com.example.inved.mynews.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.inved.mynews.controller.NyTimesSearchAPI;
import com.example.inved.mynews.searchapi.SearchResult;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.inved.mynews.controller.AbsNyTimesFragment.API_KEY;

public class MyAsyncTaskLoaderSearch extends AsyncTaskLoader<SearchResult> {

    private String query;
    private String filter;

    public MyAsyncTaskLoaderSearch(Context context, String query, String filter) {

        super(context);
        this.query = query;
        this.filter = filter;

    }


    @Override
    public SearchResult loadInBackground() {

        Call<SearchResult> nyTimesSearchCall = service.getNyTimesSearchAPI(query, filter, API_KEY);

        Response<SearchResult> responseBusiness = null;
        try {
            responseBusiness = nyTimesSearchCall.execute(); //on reste bloqué ici tant que pas fini
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseBusiness == null || responseBusiness.body() == null) {
            return null;
        } else

            return responseBusiness.body();
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
            .baseUrl("https://api.nytimes.com/svc/search/v2/") //API location
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build(); //Par défaut

    private NyTimesSearchAPI service = retrofit.create(NyTimesSearchAPI.class); // nomInterface service = retrofit.create(nomInterface.class)

}
