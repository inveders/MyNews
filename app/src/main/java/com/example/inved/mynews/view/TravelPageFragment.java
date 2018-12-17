package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.example.inved.mynews.controller.AbsNyTimesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TravelPageFragment extends AbsNyTimesFragment {


    public static TravelPageFragment newInstance(String name) {
        sectionName =name;
        Log.d("DEBAGO","TravelPageFragment"+name);
        return (new TravelPageFragment());
    }

    @Override
    public String getTitle() {
        return "Travel";
    }


}


