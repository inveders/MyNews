package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class TravelPageFragment extends AbsNyTimesFragment {


    public static TravelPageFragment newInstance() {
        return (new TravelPageFragment());
    }

    @Override
    public String getTitle() {
        return "Travel";
    }

    @Override
    public LoaderManager getSupportLoaderManager() {
        return super.getSupportLoaderManager();
    }
}


