package com.example.inved.mynews.view;

import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.example.inved.mynews.controller.AbsNyTimesFragment;

public class SciencePageFragment extends AbsNyTimesFragment {


    public static SciencePageFragment newInstance(String name,String key) {
        sectionName =name;
        apiKey = key;
        Log.d("DEBAGO","SciencePageFragment"+name);
        return (new SciencePageFragment());
    }


    @Override
    public String getTitle() {
        return "Science";
    }


}

