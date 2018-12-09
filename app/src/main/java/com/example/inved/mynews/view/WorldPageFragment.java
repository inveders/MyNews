package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;

import com.example.inved.mynews.controller.AbsNyTimesFragment;


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


}
