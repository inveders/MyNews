package com.example.inved.mynews.controller;

import android.os.Bundle;
import android.util.Log;

public class GeneralPageFragment extends AbsNyTimesFragment {


    public static GeneralPageFragment newInstance(String name) {

        Bundle arg = new Bundle(1);
        arg.putString(KEY_ARG_SECTION,name);

        Log.d("DEBAGO","GeneralPageFragment "+name+" "+KEY_ARG_SECTION);
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

