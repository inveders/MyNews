package com.example.inved.mynews.controller;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.inved.mynews.R;
import com.example.inved.mynews.topstoriesapi.Result;
import com.example.inved.mynews.utils.MyAsyncTaskLoader;
import com.example.inved.mynews.utils.MyAsyncTaskLoaderMostPopular;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */



public abstract class AbsNyTimesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Result>> {


    /*protected*/public static final String KEY_ARG_SECTION="KEY_ARG_SECTION";
    protected static final String KEY_ARG_PERIOD="KEY_ARG_PERIOD";

    /**Create a static task id that will identify our loader*/
    LoaderManager mLoaderManager;
    RecyclerViewAdapter mRecyclerViewAdapter;

    public static final String API_KEY ="69b33155fef846e29c9753f95e628397";



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general, container, false);

        //RecyclerView initialization
        RecyclerView mRecyclerView = v.findViewById(R.id.fragment_general_recycler_view);
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        //Choose how to display the list in the RecyclerView (vertical or horizontal)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false));

        //LoaderManager initialization
        mLoaderManager = getLoaderManager();
        if(mLoaderManager.getLoader(1)!=null){
            mLoaderManager.initLoader(1,null,this);


        }


        //Launch AsyncTaskLoader

        this.startAsyncTaskLoader();
        this.resumeAsyncTaskLoaderIfPossible();

        return v;
    }

    public abstract String getTitle();

    public abstract boolean isMostPopular();



    /**Start a new AsyncTaskLoader*/
    private void startAsyncTaskLoader(){


        mLoaderManager.initLoader(1,null,this);
    }

    /**Resume previous AsyncTaskLoader if still running*/
    private void resumeAsyncTaskLoaderIfPossible(){

    }

    /**Implements callback methods of the Loader Manager*/
    @NonNull
    @Override
    public Loader<List<Result>> onCreateLoader(int i, @Nullable Bundle bundle) {

        if (isMostPopular()) {

            return new MyAsyncTaskLoaderMostPopular(getContext(),getArguments().getString(KEY_ARG_SECTION),getArguments().getInt(KEY_ARG_PERIOD));

        }
        else {

            return new MyAsyncTaskLoader(getContext(),getArguments().getString(KEY_ARG_SECTION));
        }

    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Result>> loader, List<Result> results) {

        mRecyclerViewAdapter.setData(results);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Result>> loader) {

    }




}




