package com.example.inved.mynews.utils;

import android.app.job.JobService;

import androidx.annotation.Nullable;

import java.util.Collection;

public class CollectionUtils {


    public static boolean isEmpty(@Nullable Collection collection){
        return collection == null || collection.isEmpty();
    }

}
