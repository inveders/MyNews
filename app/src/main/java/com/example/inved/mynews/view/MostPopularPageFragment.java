package com.example.inved.mynews.view;


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
import com.example.inved.mynews.controller.RecyclerViewAdapterMostPopular;
import com.example.inved.mynews.mostpopularapi.ResultMostPopular;
import com.example.inved.mynews.topstoriesapi.Result;
import com.example.inved.mynews.utils.MyAsyncTaskLoaderMostPopular;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<ResultMostPopular>> {


    public static MostPopularPageFragment newInstance() {

        Log.d("DEBAGO","MostPopularPageFragment");
        return (new MostPopularPageFragment());
    }


    /**Create a static task id that will identify our loader*/
    LoaderManager mLoaderManager;
    RecyclerViewAdapterMostPopular mRecyclerViewAdapterMostPopular;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_most_popular_page, container, false);
        Log.d("DEBAGO","MostPopularPageFragment");
        //RecyclerView initialization
        RecyclerView mRecyclerViewMostPopular = v.findViewById(R.id.fragment_most_popular_recycler_view);
        mRecyclerViewAdapterMostPopular = new RecyclerViewAdapterMostPopular();
        mRecyclerViewMostPopular.setAdapter(mRecyclerViewAdapterMostPopular);

        //Choose how to display the list in the RecyclerView (vertical or horizontal)
        mRecyclerViewMostPopular.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));

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
    public Loader<List<ResultMostPopular>> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new MyAsyncTaskLoaderMostPopular(getContext());
    }


    @Override
    public void onLoadFinished(@NonNull Loader<List<ResultMostPopular>> loader, List<ResultMostPopular> results) {

        mRecyclerViewAdapterMostPopular.setData(results);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<ResultMostPopular>> loader) {

    }


}
