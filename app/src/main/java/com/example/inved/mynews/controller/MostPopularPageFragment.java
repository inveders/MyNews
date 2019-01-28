package com.example.inved.mynews.controller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.inved.mynews.R;
import com.example.inved.mynews.topstoriesapi.Result;
import com.example.inved.mynews.utils.MyAsyncTaskLoader;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends AbsNyTimesFragment {


    public static MostPopularPageFragment newInstance(String name) {

        Bundle arg = new Bundle(2);
        arg.putString(KEY_ARG_SECTION,name);

        Log.d("DEBAGO","MostPopularPageFragment "+name+""+KEY_ARG_SECTION);
        MostPopularPageFragment mostPopularPageFragment = new MostPopularPageFragment();
        mostPopularPageFragment.setArguments(arg); //Le fragment est lié à la bundle
        arg.putInt(KEY_ARG_PERIOD,7);
        mostPopularPageFragment.setArguments(arg);
        return mostPopularPageFragment;

    }



    @Override
    public String getTitle() {
        return getArguments().getString(KEY_ARG_SECTION);
    }

    @Override
    public boolean isMostPopular() {

        return true;
    }
}
