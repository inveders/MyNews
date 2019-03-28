package com.example.inved.mynews.controller;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends AbsNyTimesFragment {


    static MostPopularPageFragment newInstance(String name) {

        Bundle arg = new Bundle(2);
        arg.putString(KEY_ARG_SECTION,name);

        MostPopularPageFragment mostPopularPageFragment = new MostPopularPageFragment();
        mostPopularPageFragment.setArguments(arg); //Le fragment est lié à la bundle
        arg.putInt(KEY_ARG_PERIOD,7);
        mostPopularPageFragment.setArguments(arg);
        return mostPopularPageFragment;

    }



    @Override
    public String getTitle() {
        return getArguments().getString(KEY_ARG_SECTION);
    }

    @Override
    public boolean isMostPopular() {

        return true;
    }
}
