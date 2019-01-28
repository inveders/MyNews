package com.example.inved.mynews.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.inved.mynews.Themes;

public class PageAdapter extends FragmentPagerAdapter {

    // 2 - Default Constructor
    private PageAdapter(FragmentManager mgr) {
        super(mgr);

    }


    @Override
    public int getCount() {
        return 8; // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        if (Themes.values()[position].getIsMostPopular())
            return MostPopularPageFragment.newInstance(Themes.values()[position].getName());
        else
            return GeneralPageFragment.newInstance(Themes.values()[position].getName());
    }


    @Override
    public CharSequence getPageTitle(int position) {

        if (Themes.values()[position].getIsMostPopular())
            return Themes.values()[position].getTitle();
        else
            return Themes.values()[position].getTitle();

    }

}
