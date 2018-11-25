package com.example.inved.mynews.View;


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
public class WorldPageFragment extends Fragment {


    public static WorldPageFragment newInstance() {
        return (new WorldPageFragment());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_world_page, container, false);
    }
}
