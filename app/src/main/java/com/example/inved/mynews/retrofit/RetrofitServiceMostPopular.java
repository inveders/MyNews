package com.example.inved.mynews.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitServiceMostPopular {


    private static Retrofit retrofit = null;

    static NyTimesMostPopularAPI getApiServiceMostPopular() {

        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit.create(NyTimesMostPopularAPI.class);
    }

}
