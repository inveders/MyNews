package com.example.inved.mynews.controller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inved.mynews.R;
import com.example.inved.mynews.model.topstories.Result;
import com.example.inved.mynews.utils.MyAsyncTaskLoader;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */



public abstract class AbsNyTimesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Result>> {

    /**Create a static task id that will identify our loader*/
    LoaderManager mLoaderManager;
    RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general, container, false);

        //RecyclerView mRecyclerView = v.findViewById(R.id.fragment_general_page_title);
        //generalFragmentPageTitle.setText(getTitle());

        RecyclerView mRecyclerView = v.findViewById(R.id.recycler_view);
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mLoaderManager = getLoaderManager();
        this.startAsyncTaskLoader();
        this.resumeAsyncTaskLoaderIfPossible();

        if(mLoaderManager.getLoader(1)!=null){
            mLoaderManager.initLoader(1,null,this);
        }

        return v;
    }

    public abstract String getTitle();

    /**Start a new AsyncTaskLoader*/
    private void startAsyncTaskLoader(){

        mLoaderManager.initLoader(1,null,this);
    }

  /*  /**Configure LoaderManager*/
   /* public LoaderManager getSupportLoaderManager() {
        return supportLoaderManager;
    }*/

    /**Resume previous AsyncTaskLoader if still running*/
    private void resumeAsyncTaskLoaderIfPossible(){

    }

    /**Implements callback methods of the Loader Manager*/
    @NonNull
    @Override
    public Loader<List<Result>> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.d("DEBAGO","onCreateLoader");
        return new MyAsyncTaskLoader(getContext());
    }


    @Override
    public void onLoadFinished(@NonNull Loader<List<Result>> loader, List<Result> results) {

        mRecyclerViewAdapter.setData(results);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Result>> loader) {

    }

}




