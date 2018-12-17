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


public class TopStoriesPageFragment extends AbsNyTimesFragment {


    public static TopStoriesPageFragment newInstance(String name) {
        sectionName =name;

        Log.d("DEBAGO","TopStoriesPageFragment"+name);
        return (new TopStoriesPageFragment());
    }

    @Override
    public String getTitle() {
        return "Top Stories";
    }


}