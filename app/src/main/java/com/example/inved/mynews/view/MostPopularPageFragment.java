package com.example.inved.mynews.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inved.mynews.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends Fragment {


    public static MostPopularPageFragment newInstance() {
        return (new MostPopularPageFragment());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_most_popular_page, container, false);
    }
}
