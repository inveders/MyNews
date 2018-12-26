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
import android.widget.LinearLayout;

import com.example.inved.mynews.R;
import com.example.inved.mynews.topstoriesapi.Result;
import com.example.inved.mynews.utils.MyAsyncTaskLoader;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends AbsNyTimesFragment {


    public static MostPopularPageFragment newInstance(String name, int period) {

        Bundle arg = new Bundle(2);
        arg.putString(KEY_ARG_SECTION,name);

        Log.d("DEBAGO","MostPopularPageFragment "+name+""+KEY_ARG_SECTION);
        MostPopularPageFragment mostPopularPageFragment = new MostPopularPageFragment();
        mostPopularPageFragment.setArguments(arg); //Le fragment est lié à la bundle
        arg.putInt(KEY_ARG_PERIOD,period);
        mostPopularPageFragment.setArguments(arg);
        return mostPopularPageFragment;

    }


    @Override
    public String getTitle() {
        return getArguments().getString(KEY_ARG_SECTION);
    }


    /**Create a static task id that will identify our loader*/
    LoaderManager mLoaderManager;
    RecyclerViewAdapter mRecyclerViewAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_most_popular_page, container, false);
        //RecyclerView initialization
        RecyclerView mRecyclerView = v.findViewById(R.id.fragment_most_popular_recycler_view);
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        //Choose how to display the list in the RecyclerView (vertical or horizontal)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));

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


    /**Start a new AsyncTaskLoader : MyAsycTaskLoaderMostPopular*/
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
