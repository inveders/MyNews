package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.example.inved.mynews.controller.AbsNyTimesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorldPageFragment extends AbsNyTimesFragment {


    public static WorldPageFragment newInstance(String name) {
        sectionName =name;
        Log.d("DEBAGO","WorldPageFragment"+name);
        return (new WorldPageFragment());
    }


    @Override
    public String getTitle() {
        return "World";
    }


}
