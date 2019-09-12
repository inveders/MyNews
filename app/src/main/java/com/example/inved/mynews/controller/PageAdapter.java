package com.example.inved.mynews.controller;

import com.example.inved.mynews.Themes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    // Default Constructor
    PageAdapter(FragmentManager mgr) {
        super(mgr,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    }


    @Override
    public int getCount() {
        return 8; // Number of page to show
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (Themes.values()[position].getIsMostPopular()){

            return MostPopularPageFragment.newInstance(Themes.values()[position].getName());
        }
        else{

            return GeneralPageFragment.newInstance(Themes.values()[position].getName());
        }

    }


    @Override
    public CharSequence getPageTitle(int position) {

        if (Themes.values()[position].getIsMostPopular())
            return Themes.values()[position].getTitle();
        else
            return Themes.values()[position].getTitle();

    }

}
