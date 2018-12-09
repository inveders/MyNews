package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;

import com.example.inved.mynews.controller.AbsNyTimesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodPageFragment extends AbsNyTimesFragment {


    public static FoodPageFragment newInstance() {
        return (new FoodPageFragment());
    }

    @Override
    public String getTitle() {
        return "Food";
    }


}
