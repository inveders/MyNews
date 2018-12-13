package com.example.inved.mynews.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.inved.mynews.view.FoodPageFragment;
import com.example.inved.mynews.view.MostPopularPageFragment;
import com.example.inved.mynews.view.SciencePageFragment;
import com.example.inved.mynews.view.SportsPageFragment;
import com.example.inved.mynews.view.TechPageFragment;
import com.example.inved.mynews.view.TopStoriesPageFragment;
import com.example.inved.mynews.view.TravelPageFragment;
import com.example.inved.mynews.view.WorldPageFragment;

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
                return TopStoriesPageFragment.newInstance("home","69b33155fef846e29c9753f95e628397");
            case 1: //Page number 2
                return MostPopularPageFragment.newInstance("all-sections","69b33155fef846e29c9753f95e628397",7);
            case 2: //Page number 3
                return TechPageFragment.newInstance("technology","69b33155fef846e29c9753f95e628397");
            case 3: //Page number 4
                return SciencePageFragment.newInstance("science","69b33155fef846e29c9753f95e628397");
            case 4: //Page number 5
                return SportsPageFragment.newInstance("sports","69b33155fef846e29c9753f95e628397");
            case 5: //Page number 6
                return FoodPageFragment.newInstance("food","69b33155fef846e29c9753f95e628397");
            case 6: //Page number 7
                return TravelPageFragment.newInstance("travel","69b33155fef846e29c9753f95e628397");
            case 7: //Page number 8
                return WorldPageFragment.newInstance("world","69b33155fef846e29c9753f95e628397");
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
