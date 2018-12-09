package com.example.inved.mynews.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inved.mynews.R;


public class TopStoriesPageFragment extends Fragment {


    public static TopStoriesPageFragment newInstance() {
        return (new TopStoriesPageFragment());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_stories_page, container, false);
    }
}