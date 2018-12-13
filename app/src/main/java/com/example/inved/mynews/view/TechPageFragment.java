package com.example.inved.mynews.view;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.inved.mynews.controller.AbsNyTimesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TechPageFragment extends AbsNyTimesFragment {



    public static TechPageFragment newInstance(String name,String key) {
        sectionName =name;
        apiKey = key;
        Log.d("DEBAGO","TechPageFragment"+name);
        return (new TechPageFragment());
    }

    @Override
    public String getTitle() {
        return "Tech";
    }



}
