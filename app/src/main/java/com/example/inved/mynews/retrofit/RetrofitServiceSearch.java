package com.example.inved.mynews.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceSearch {


    private static Retrofit retrofit = null;

    public static NyTimesSearchAPI getApiServiceSearch() {

        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();


            retrofit = new Retrofit.Builder() //Par défaut
                    .baseUrl("https://api.nytimes.com/svc/search/v2/") //API location
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build(); //Par défaut
        }




        return retrofit.create(NyTimesSearchAPI.class);
    }


}



