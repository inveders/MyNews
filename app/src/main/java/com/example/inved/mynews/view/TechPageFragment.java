package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TechPageFragment extends AbsNyTimesFragment {


    public static TechPageFragment newInstance() {
        return (new TechPageFragment());
    }

    @Override
    public String getTitle() {
        return "Tech";
    }
}
