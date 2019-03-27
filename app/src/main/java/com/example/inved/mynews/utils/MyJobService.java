package com.example.inved.mynews.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.inved.mynews.R;
import com.example.inved.mynews.controller.NotificationActivity;
import com.example.inved.mynews.searchapi.SearchResult;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.joda.time.DateTime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MyJobService extends JobService implements LoaderManager.LoaderCallbacks<SearchResult>{


    Context context;
    int notificationId = 1;
    NotificationActivity notificationActivity = new NotificationActivity();
    String mQuery = notificationActivity.getmQueryNotif();
    String mFilter = notificationActivity.getmFilterNotif();
    String notificationTitle = "Nouveaux articles";
    String notificationText;
    public static final String CHANNEL_ID = "1";
    private DateTime currentDate = new DateTime();


    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        startAsyncTaskLoaderSearch();
        context = getBaseContext();
        createNotificationChannel();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builderNotification.build());


        try {
// moi je rajoute souvent un TRY en cas de problème sur une opération risqué
            Log.d("JOBB", "OK");
//simple log dans le logcat
            Toast.makeText(context, "test jobscheduler ok",
                    Toast.LENGTH_LONG).show();
// on affiche "test ok "
        } catch (Exception x) {
// rien en cas d\'echec dans la condition TRY
        }

        return true;

    }

    /**
     * Start a new AsyncTaskLoader
     */
    private void startAsyncTaskLoaderSearch() {
        //LoaderManager initialization

     //  getSupportLoaderManager().initLoader(1,null,this);
    }

  /*  private LoaderManager getSupportLoaderManager() {
        return getSupportLoaderManager();
    }*/

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d("JOBB", "JOB TERMINE");
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



    public void createNotificationChannel() {
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
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }


    public String getNewArticlesCount(int countArticles) {

        if (countArticles == 0) {
            notificationText = "Il n'y a pas de nouveaux articles depuis hier";
        } else if (countArticles == 1) {
            notificationText = "Il y a " + countArticles + " nouvel article depuis hier";
        } else {
            notificationText = "Il y a " + countArticles + " nouveaux articles depuis hier";
        }
        Log.d("debagaa", "nombre d'articles "+countArticles);
        return notificationText;
    }

    @NonNull
    @Override
    public Loader<SearchResult> onCreateLoader(int id, @Nullable Bundle args) {
        return new MyAsyncTaskLoaderSearch(this, mQuery, mFilter, currentDate.minusHours(24).toString(), currentDate.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<SearchResult> loader, SearchResult data) {
        if (data != null && data.response != null && data.response.docs != null) {
            Log.d("DEBAGaa", "Nombre de résultat des notifications " + data.response.docs.size());
            getNewArticlesCount(data.response.docs.size());
        } else {
            getNewArticlesCount(0);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<SearchResult> loader) {

    }
}

