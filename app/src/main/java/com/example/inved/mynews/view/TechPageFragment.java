package com.example.inved.mynews.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.example.inved.mynews.controller.AbsNyTimesFragment;
import com.example.inved.mynews.controller.RecyclerViewAdapter;
import com.example.inved.mynews.model.topstories.Result;
import com.example.inved.mynews.utils.MyAsyncTaskLoader;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TechPageFragment extends AbsNyTimesFragment {



    public static TechPageFragment newInstance() {
        return (new TechPageFragment());
    }

    @Override
    public String getTitle() {
        return "Tech";
    }



}
