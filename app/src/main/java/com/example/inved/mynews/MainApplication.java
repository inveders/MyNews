package com.example.inved.mynews;

import android.app.Application;
import android.content.res.Resources;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainApplication extends Application {

    private static MainApplication mInstance;
    private static Resources res;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        mInstance = this;
        res = getResources();
    }

    public static MainApplication getInstance() {
        return mInstance;
    }

    public static Resources getResourses() {
        return res;
    }
}
