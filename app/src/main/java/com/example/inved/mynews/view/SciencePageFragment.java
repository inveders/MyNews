package com.example.inved.mynews.view;

import android.support.v4.app.LoaderManager;

import com.example.inved.mynews.controller.AbsNyTimesFragment;

public class SciencePageFragment extends AbsNyTimesFragment {


    public static SciencePageFragment newInstance() {
        return (new SciencePageFragment());
    }

    @Override
    public String getTitle() {
        return "Science";
    }


}

