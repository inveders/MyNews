package com.example.inved.mynews.retrofit;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceTopStories {



    private static Retrofit retrofit = null;

    public static NyTimesAPI getApiServiceTopStories() {

        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();


            retrofit = new Retrofit.Builder() //Par défaut
                    .baseUrl("https://api.nytimes.com/svc/topstories/v2/") //API location
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build(); //Par défaut
        }




        return retrofit.create(NyTimesAPI.class);
    }


}
