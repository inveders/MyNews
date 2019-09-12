package com.example.inved.mynews.controller.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralPageFragment extends AbsNyTimesFragment {


    public static GeneralPageFragment newInstance(String name) {

        Bundle arg = new Bundle(1);
        arg.putString(KEY_ARG_SECTION, name);

        GeneralPageFragment generalPageFragment = new GeneralPageFragment();
        //Le fragment est lié à la bundle
        generalPageFragment.setArguments(arg);
        return generalPageFragment;

    }

    @Override

    public String getTitle() {
        return getArguments() != null ? getArguments().getString(KEY_ARG_SECTION) : null;
    }

    @Override
    public boolean isMostPopular() {

        return false;
    }


}

