package com.example.inved.mynews.controller;

import android.os.Bundle;

public class GeneralPageFragment extends AbsNyTimesFragment {


    static GeneralPageFragment newInstance(String name) {

        Bundle arg = new Bundle(1);
        arg.putString(KEY_ARG_SECTION, name);

        GeneralPageFragment generalPageFragment = new GeneralPageFragment();
        generalPageFragment.setArguments(arg); //Le fragment est lié à la bundle
        return generalPageFragment;

    }

    @Override
    public String getTitle() {
        return getArguments().getString(KEY_ARG_SECTION);
    }

    @Override
    public boolean isMostPopular() {

        return false;
    }
}

