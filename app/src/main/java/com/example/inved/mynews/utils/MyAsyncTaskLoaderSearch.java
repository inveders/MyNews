package com.example.inved.mynews.utils;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;
import android.util.Log;

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
    private String beginDate;
    private String endDate;

    public MyAsyncTaskLoaderSearch(Context context, String query, String filter, String beginDate, String endDate) {

        super(context);
        this.query = query;
        this.filter = filter;
        this.beginDate = beginDate;
        this.endDate = endDate;

    }

    @Override
    public SearchResult loadInBackground() {

        Call<SearchResult> nyTimesSearchCall = service.getNyTimesSearchAPI(query, filter, beginDate, endDate, API_KEY);

        Response<SearchResult> responseBusiness = null;
        try {
            responseBusiness = nyTimesSearchCall.execute(); //on reste bloqué ici tant que pas fini
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseBusiness == null || responseBusiness.body() == null) {
            Log.d("DEBAGa", "loadInBackground pb");
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
            .baseUrl("https://api.nytimes.com/svc/activity_search/v2/") //API location
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build(); //Par défaut

    private NyTimesSearchAPI service = retrofit.create(NyTimesSearchAPI.class); // nomInterface service = retrofit.create(nomInterface.class)

}
