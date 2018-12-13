package com.example.inved.mynews.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inved.mynews.R;
import com.example.inved.mynews.controller.AbsNyTimesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends AbsNyTimesFragment {


    public static MostPopularPageFragment newInstance(String name, String key, int period) {
        sectionName =name;
        apiKey = key;
        articlePeriod=period;
        Log.d("DEBAGO","MostPopularPageFragment"+name);
        return (new MostPopularPageFragment());
    }

    @Override
    public String getTitle() {
        return "MostPopularPageFragment";
    }

}
