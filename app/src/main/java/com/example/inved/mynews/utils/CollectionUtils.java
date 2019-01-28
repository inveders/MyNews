package com.example.inved.mynews.utils;

import android.support.annotation.Nullable;

import java.util.Collection;

public class CollectionUtils {


    public static boolean isEmpty(@Nullable Collection collection){
        return collection == null || collection.isEmpty();
    }

}
