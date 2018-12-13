package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.example.inved.mynews.controller.AbsNyTimesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SportsPageFragment extends AbsNyTimesFragment {


    public static SportsPageFragment newInstance(String name,String key) {
        sectionName =name;
        apiKey = key;
        Log.d("DEBAGO","SportsPageFragment"+name);
        return (new SportsPageFragment());
    }

    @Override
    public String getTitle() {
        return "Sports";
    }


}

