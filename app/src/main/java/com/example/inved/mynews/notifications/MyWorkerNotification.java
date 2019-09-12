package com.example.inved.mynews.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.inved.mynews.R;
import com.example.inved.mynews.retrofit.NyTimesSearchAPI;
import com.example.inved.mynews.searchapi.SearchResult;
import com.example.inved.mynews.utils.MainApplication;

import org.joda.time.DateTime;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.inved.mynews.controller.fragments.AbsNyTimesFragment.API_KEY;

public class MyWorkerNotification extends Worker {

    static final String EXTRA_QUERY = "EXTRA_QUERY";
    static final String EXTRA_FILTER = "EXTRA_FILTER";

    private static final String CHANNEL_ID = "CHANNEL_1";
    private String notificationTitle = MainApplication.getResourses().getString(R.string.newArticlesJobService);
    private String notificationText;
    private DateTime currentDate = new DateTime();


    public MyWorkerNotification(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {


        String mQueryNotif = getInputData().getString(EXTRA_QUERY);
        String mFilterNotif = getInputData().getString(EXTRA_FILTER);

        retrofitCall(mQueryNotif, mFilterNotif);


        return Result.success();

    }



    private void createNotification() {

        NotificationCompat.Builder builderNotification = new NotificationCompat.Builder
                (MainApplication.getInstance().getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_fiber_new_black_24dp)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText).setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setVisibility(NotificationCompat.VISIBILITY_SECRET);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(
                MainApplication.getInstance().getApplicationContext());
        int notificationId = 1;
        notificationManager.notify(notificationId, builderNotification.build());
        createNotificationChannel();
    }


    private void createNotificationChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = MainApplication.getResourses().getString(R.string.channel_name);
            String description = MainApplication.getResourses().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = MainApplication.getInstance().getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }

    }


    private String getNotificationText(int articleCount) {
        String notifText;

        switch (articleCount) {
            case 0:
                notifText = MainApplication.getResourses().getString(R.string.no_new_articles);
                break;
            case 1:
                notifText = MainApplication.getResourses().getString(R.string.one_new_article);
                break;

            default:
                notifText = MainApplication.getResourses().getString(R.string.new_articles_1, articleCount);
        }

        return notifText;
    }

    //Retrofit
    private void retrofitCall(String mQuery, String mFilter) {


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

                    createNotification();


                } else {
                    notificationText = getNotificationText(0);
                    createNotification();


                }

            }

            @Override
            public void onFailure(@Nullable Call<SearchResult> call, @Nullable Throwable t) {

            }
        });

    }


}
