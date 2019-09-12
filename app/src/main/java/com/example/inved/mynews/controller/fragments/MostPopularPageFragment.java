package com.example.inved.mynews.controller.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends AbsNyTimesFragment {


    public static MostPopularPageFragment newInstance(String name) {

        Bundle arg = new Bundle(2);
        arg.putString(KEY_ARG_SECTION,name);

        MostPopularPageFragment mostPopularPageFragment = new MostPopularPageFragment();
        //Le fragment est lié à la bundle
        mostPopularPageFragment.setArguments(arg);
        arg.putInt(KEY_ARG_PERIOD,7);
        mostPopularPageFragment.setArguments(arg);
        return mostPopularPageFragment;

    }


    @Override
    public String getTitle() {
        return getArguments() != null ? getArguments().getString(KEY_ARG_SECTION) : null;
    }

    @Override
    public boolean isMostPopular() {

        return true;
    }


}
