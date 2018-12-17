package com.example.inved.mynews.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.example.inved.mynews.controller.AbsNyTimesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodPageFragment extends AbsNyTimesFragment {




    public static FoodPageFragment newInstance(String name) {

        sectionName =name;
        Log.d("DEBAGO","FoodPageFragment"+name);

        return (new FoodPageFragment());

    }


    @Override
    public String getTitle() {
        return "Food";
    }


}

