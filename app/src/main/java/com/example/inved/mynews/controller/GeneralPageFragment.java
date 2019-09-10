package com.example.inved.mynews.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.Loader;

import com.example.inved.mynews.topstoriesapi.Result;

import java.util.List;

public class GeneralPageFragment extends AbsNyTimesFragment {


    static GeneralPageFragment newInstance(String name) {

        Bundle arg = new Bundle(1);
        arg.putString(KEY_ARG_SECTION, name);

        GeneralPageFragment generalPageFragment = new GeneralPageFragment();
        generalPageFragment.setArguments(arg); //Le fragment est lié à la bundle
        return generalPageFragment;

    }

    @Override
    public String getTitle() {
        return getArguments().getString(KEY_ARG_SECTION);
    }

    @Override
    public boolean isMostPopular() {

        return false;
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

