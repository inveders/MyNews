package com.example.inved.mynews.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import com.example.inved.mynews.R;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyJobService extends JobService {


    String notificationTitle = "My notifications";
    String notificationText = "Je ne peux m'empêcher de parler";
    int notificationId = 1;
    public static final String CHANNEL_ID = "1";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        createNotificationChannel();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builderNotification.build());
        return true;

    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return false;
    }

    /***Creation of the notification*/
    NotificationCompat.Builder builderNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_fiber_new_black_24dp)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            /* .setStyle(new NotificationCompat.BigTextStyle()
                     .bigText(longerTextContent))*/
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET);


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
