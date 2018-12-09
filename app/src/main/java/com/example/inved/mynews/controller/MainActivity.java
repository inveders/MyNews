package com.example.inved.mynews.controller;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;

import com.example.inved.mynews.R;
import com.example.inved.mynews.utils.MyAsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<String>>{

    List<String> results = new ArrayList<>();
    /**Create a static task id that will identify our loader*/
    private static int TASK_TOP_STORIES_ID = 1;
    private LoaderManager supportLoaderManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startAsyncTaskLoader();
        this.resumeAsyncTaskLoaderIfPossible();
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);

        RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(results);
        Log.d("DEBAGO","Main Activity 1");
        //Choisir comment on affiche notre liste (vertical, horizonthal)
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

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

    /**Start a new AsyncTaskLoader*/
    private void startAsyncTaskLoader(){
        Log.d("DEBAGO","Main Activity start AsyncTaskLoader");
      //  getSupportLoaderManager().restartLoader(TASK_TOP_STORIES_ID, null, this);
    }

    /**Configure LoaderManager*/
    public LoaderManager getSupportLoaderManager() {

        return supportLoaderManager;
    }

    /**Resume previous AsyncTaskLoader if still running*/
    private void resumeAsyncTaskLoaderIfPossible(){
        if (getSupportLoaderManager().getLoader(TASK_TOP_STORIES_ID) != null && Objects.requireNonNull(getSupportLoaderManager().getLoader(TASK_TOP_STORIES_ID)).isStarted()) {
            getSupportLoaderManager().initLoader(TASK_TOP_STORIES_ID, null, this);

        }
    }

    /**Implements callback methods of the Loader Manager*/
    @NonNull
    @Override
    public Loader<List<String>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new MyAsyncTaskLoader(this);
    }


    @Override
    public void onLoadFinished(@NonNull Loader<List<String>> loader, List<String> results) {
        loader.stopLoading();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<String>> loader) {

    }





}

