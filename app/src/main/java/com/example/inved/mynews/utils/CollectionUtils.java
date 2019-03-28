package com.example.inved.mynews.utils;

import java.util.Collection;

import androidx.annotation.Nullable;

public class CollectionUtils {


    public static boolean isEmpty(@Nullable Collection collection){
        return collection == null || collection.isEmpty();
    }

}
