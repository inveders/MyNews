package com.example.inved.mynews;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.inved.mynews.controller.NotificationActivity;
import com.example.inved.mynews.utils.Util;

public class MyStartServiceReceiver extends BroadcastReceiver {

    NotificationActivity notificationActivity;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(notificationActivity.notificationActionIfEnabled()) {
            Util.scheduleJob(context);
        }

    }
}