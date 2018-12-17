package com.example.inved.mynews.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.inved.mynews.controller.NyTimesMostPopularAPI;
import com.example.inved.mynews.mostpopularapi.NyTimesMostPopular;
import com.example.inved.mynews.mostpopularapi.ResultMostPopular;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.inved.mynews.controller.AbsNyTimesFragment.API_KEY;

public class MyAsyncTaskLoaderMostPopular extends AsyncTaskLoader<List<ResultMostPopular>> {


    /**Constructor*/
    public MyAsyncTaskLoaderMostPopular(Context context){

            super(context);

            }

    @Override
    public List<ResultMostPopular> loadInBackground(){


            /**1st retrofit call*/
            Response<NyTimesMostPopular> responseMostPopular=null;
            try{
                Log.d("DEBAGO","before retrofit Most Popular");
            responseMostPopular= nyTimesMostPopularCall.execute(); //on reste bloqué ici tant que pas fini
                Log.d("DEBAGO","retrofit Most Popular");
            }catch(IOException e){
            e.printStackTrace();
            }
            if(responseMostPopular==null||responseMostPopular.body()==null){
            return null;
            }else


            return(responseMostPopular.body()).resultsMostPopular; //the Objects.requiresNonNull is not necessary

            }

    @Override
    protected void onStartLoading(){
            super.onStartLoading();
            forceLoad();
            }

    /**Retrofit A DEPLACER*/
    private OkHttpClient client=new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    /**Retrofit Top Stories API*/
    private Retrofit retrofit=new Retrofit.Builder() //Par défaut
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/") //API location
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build(); //Par défaut

    private NyTimesMostPopularAPI service=retrofit.create(NyTimesMostPopularAPI.class); // nomInterface service = retrofit.create(nomInterface.class)
    private Call<NyTimesMostPopular> nyTimesMostPopularCall =service.getNyTimesMostPopular("all-sections",7,API_KEY);

    }

