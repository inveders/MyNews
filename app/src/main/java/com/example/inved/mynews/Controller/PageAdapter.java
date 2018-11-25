package com.example.inved.mynews.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.inved.mynews.View.GeneralFragment;
import com.example.inved.mynews.View.MostPopularPageFragment;
import com.example.inved.mynews.View.TopStoriesPageFragment;

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
                return TopStoriesPageFragment.newInstance();
            case 1: //Page number 2
                return MostPopularPageFragment.newInstance();
            case 2: //Page number 3
                return GeneralFragment.newInstance(position);
            case 3: //Page number 4
                return GeneralFragment.newInstance(position);
            case 4: //Page number 5
                return GeneralFragment.newInstance(position);
            case 5: //Page number 6
                return GeneralFragment.newInstance(position);
            case 6: //Page number 7
                return GeneralFragment.newInstance(position);
            case 7: //Page number 8
                return GeneralFragment.newInstance(position);
            default:
                return null;
        }
    }



    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
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
