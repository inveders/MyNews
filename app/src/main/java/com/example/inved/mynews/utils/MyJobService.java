package com.example.inved.mynews.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.example.inved.mynews.R;
import com.example.inved.mynews.controller.NotificationActivity;
import com.example.inved.mynews.controller.NyTimesSearchAPI;
import com.example.inved.mynews.searchapi.SearchResult;

import org.joda.time.DateTime;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.inved.mynews.controller.AbsNyTimesFragment.API_KEY;


public class MyJobService extends JobService {


    Context context;
    int notificationId = 1;
    String mQuery;
    String mFilter;
    String notificationTitle = "Nouveaux articles";

    String notificationText;

    public static final String CHANNEL_ID = "1";
    private DateTime currentDate = new DateTime();


    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        mQuery = jobParameters.getExtras().getString(NotificationActivity.KEY_QUERY_BUNDLE);
        mFilter = jobParameters.getExtras().getString(NotificationActivity.KEY_FILTER_BUNDLE);

        retrofitCall();

        return true;

    }

    /**
     * Retrofit Call
     */
    private void retrofitCall() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder() //Par défaut
                .baseUrl("https://api.nytimes.com/svc/search/v2/") //API location
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build(); //Par défaut

        NyTimesSearchAPI service = retrofit.create(NyTimesSearchAPI.class);

        Call<SearchResult> nyTimesSearchCall = service.getNyTimesSearchAPI(mQuery, mFilter, currentDate.minusHours(24).toString(), currentDate.toString(), API_KEY);

        nyTimesSearchCall.enqueue(new Callback<SearchResult>() {


            @Override
            public void onResponse(@Nullable Call<SearchResult> call, @Nullable Response<SearchResult> response) {

                assert response != null;
                if (response.body() != null && response.body().response.docs != null) {

                    notificationText = getNotificationText(response.body().response.docs.size());

                    context = getBaseContext();

                    createNotification();

                    createNotificationChannel();

                } else {
                    notificationText = getNotificationText(0);
                    createNotification();
                    createNotificationChannel();

                }

            }

            @Override
            public void onFailure(@Nullable Call<SearchResult> call, @Nullable Throwable t) {

            }
        });

    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return false;
    }

    /***Creation of the notification*/
    private void createNotification() {
        NotificationCompat.Builder builderNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_fiber_new_black_24dp)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setVisibility(NotificationCompat.VISIBILITY_SECRET);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, builderNotification.build());
    }

    public void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }


    public String getNotificationText(int articleCount) {
        String notifText;

        switch (articleCount) {
            case 0:
                notifText = "Il n'y a pas de nouveaux articles depuis hier";
                break;
            case 1:
                notifText = "Il y a 1 nouvel article depuis hier";
                break;

            default:
                notifText = "Il y a " + articleCount + " nouveaux articles depuis hier";
        }

        return notifText;
    }


}

