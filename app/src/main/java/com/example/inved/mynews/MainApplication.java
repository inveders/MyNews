package com.example.inved.mynews;

import android.app.Application;
import android.content.res.Resources;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainApplication extends Application {


    private static Resources res;
    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        JodaTimeAndroid.init(this);

        res = getResources();
    }


    public static MainApplication getInstance() {
        return mInstance;
    }

    public static Resources getResourses() {
        return res;
    }
}
