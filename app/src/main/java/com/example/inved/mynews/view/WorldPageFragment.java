package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorldPageFragment extends AbsNyTimesFragment {


    public static WorldPageFragment newInstance() {
        return (new WorldPageFragment());
    }


    @Override
    public String getTitle() {
        return "World";
    }

    @Override
    public LoaderManager getSupportLoaderManager() {
        return super.getSupportLoaderManager();
    }
}
