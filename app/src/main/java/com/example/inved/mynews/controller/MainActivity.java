package com.example.inved.mynews.controller;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.inved.mynews.R;
import com.example.inved.mynews.model.topstories.NyTimesTopStories;
import com.example.inved.mynews.utils.MyAsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolbar();
        this.configureViewPagerAndTabs();

    }


    /**Creation of the toolbar*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    // ----

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }


    /**End of the creation of the toolbar*/

    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        // 1 - Get TabLayout from layout
        TabLayout tabs= findViewById(R.id.activity_main_tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width

    }


    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    /**Retrofit Top Stories API*/
    Retrofit retrofit = new Retrofit.Builder() //Par défaut
            .baseUrl("https://api.nytimes.com/") //API location
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build(); //Par défaut

    NyTimesTopStoriesAPI service = retrofit.create(NyTimesTopStoriesAPI.class); // nomInterface service = retrofit.create(nomInterface.class)

    Call<NyTimesTopStories> nyTimesTopStoriesCall = service.getNyTimesTopStories();


 /*   nyTimesTopStoriesCall.enqueue(new Callback<NyTimesTopStories>() {

        @Override
        public void onResponse(Call<NyTimesTopStories> call, Response<NyTimesTopStories> response) {
            if (response.body() != null);

            //   mBalance_text.setText(response.body().main.temp.toString());
            // mBalance_text.setText(response.body().main.temp.toString());
        }

        @Override
        public void onFailure(Call<NyTimesTopStories> call, Throwable t) {

        }
    });*/




}
