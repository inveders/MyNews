package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;

import com.example.inved.mynews.controller.AbsNyTimesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SportsPageFragment extends AbsNyTimesFragment {


    public static SportsPageFragment newInstance() {
        return (new SportsPageFragment());
    }

    @Override
    public String getTitle() {
        return "Sports";
    }


}
