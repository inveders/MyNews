package com.example.inved.mynews.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    // 2 - Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);

    }


    @Override
    public int getCount() {
        return 8; // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: //Page number 1
                return GeneralPageFragment.newInstance("home");
            case 1: //Page number 2
                return MostPopularPageFragment.newInstance("all-sections",7);
            case 2: //Page number 3
                return GeneralPageFragment.newInstance("technology");
            case 3: //Page number 4
                return GeneralPageFragment.newInstance("science");
            case 4: //Page number 5
                return GeneralPageFragment.newInstance("sports");
            case 5: //Page number 6
                return GeneralPageFragment.newInstance("food");
            case 6: //Page number 7
                return GeneralPageFragment.newInstance("travel");
            case 7: //Page number 8
                return GeneralPageFragment.newInstance("world");
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: //Page number 1
                return "TOP STORIES";
            case 1: //Page number 2
                return "MOST POPULAR";
            case 2: //Page number 3
                return "TECH";
            case 3: //Page number 4
                return "SCIENCE";
            case 4: //Page number 5
                return "SPORTS";
            case 5: //Page number 6
                return "FOOD";
            case 6: //Page number 7
                return "TRAVEL";
            case 7: //Page number 8
                return "WORLD";
            default:
                return null;
        }
    }

}
