package com.example.inved.mynews.controller;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.Loader;

import com.example.inved.mynews.topstoriesapi.Result;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends AbsNyTimesFragment {


    static MostPopularPageFragment newInstance(String name) {

        Bundle arg = new Bundle(2);
        arg.putString(KEY_ARG_SECTION,name);

        MostPopularPageFragment mostPopularPageFragment = new MostPopularPageFragment();
        mostPopularPageFragment.setArguments(arg); //Le fragment est lié à la bundle
        arg.putInt(KEY_ARG_PERIOD,7);
        mostPopularPageFragment.setArguments(arg);
        return mostPopularPageFragment;

    }


    @Override
    public String getTitle() {
        return getArguments() != null ? getArguments().getString(KEY_ARG_SECTION) : null;
    }

    @Override
    public boolean isMostPopular() {

        return true;
    }

    @NonNull
    @Override
    public Loader<List<Result>> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Result>> loader, List<Result> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Result>> loader) {

    }
}
