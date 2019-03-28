package com.example.inved.mynews.controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.inved.mynews.MemorizedArticlesDAO;
import com.example.inved.mynews.R;
import com.example.inved.mynews.topstoriesapi.Result;
import com.example.inved.mynews.utils.MyAsyncTaskLoader;
import com.example.inved.mynews.utils.MyAsyncTaskLoaderMostPopular;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */


public abstract class AbsNyTimesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Result>> {


    protected static final String KEY_ARG_SECTION = "KEY_ARG_SECTION";
    protected static final String KEY_ARG_PERIOD = "KEY_ARG_PERIOD";
    private MemorizedArticlesDAO mMemorizedArticlesDAO;

    /**
     * Create a static task id that will identify our loader
     */
    private LoaderManager mLoaderManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;


    public static final String API_KEY = "69b33155fef846e29c9753f95e628397";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general, container, false);

        //RecyclerView initialization
        RecyclerView mRecyclerView = v.findViewById(R.id.fragment_general_recycler_view);
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        //Choose how to display the list in the RecyclerView (vertical or horizontal)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));

        //LoaderManager initialization
        mLoaderManager = getLoaderManager();
        if (mLoaderManager.getLoader(1) != null) {
            mLoaderManager.initLoader(1, null, this);


        }

        mMemorizedArticlesDAO = new MemorizedArticlesDAO(Objects.requireNonNull(getActivity()));

        //Launch AsyncTaskLoader
        this.startAsyncTaskLoader();
        this.resumeAsyncTaskLoaderIfPossible();

        return v;
    }

    public abstract String getTitle();

    public abstract boolean isMostPopular();


    /**
     * Start a new AsyncTaskLoader
     */
    private void startAsyncTaskLoader() {

        mLoaderManager.initLoader(1, null, this);
    }

    /**
     * Resume previous AsyncTaskLoader if still running
     */
    private void resumeAsyncTaskLoaderIfPossible() {

    }

    /**
     * Implements callback methods of the Loader Manager
     */
    @NonNull
    @Override
    public Loader<List<Result>> onCreateLoader(int i, @Nullable Bundle bundle) {

        if (isMostPopular()) {

            return new MyAsyncTaskLoaderMostPopular(getContext(), getArguments().getString(KEY_ARG_SECTION), getArguments().getInt(KEY_ARG_PERIOD));

        } else {

            return new MyAsyncTaskLoader(getContext(), getArguments().getString(KEY_ARG_SECTION));
        }

    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Result>> loader, List<Result> results) {

        Set<String> listCommonUrl = new HashSet<>();

        for (int i = 0; i < results.size(); i++) {
            if (mMemorizedArticlesDAO.findMemorizedArticle(results.get(i).url)) {
                listCommonUrl.add(results.get(i).url);
            }

        }
        mRecyclerViewAdapter.setArticleMemorized(listCommonUrl);
        mRecyclerViewAdapter.setData(results);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Result>> loader) {

    }


}




