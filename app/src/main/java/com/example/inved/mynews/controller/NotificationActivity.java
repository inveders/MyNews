package com.example.inved.mynews.controller;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.inved.mynews.R;
import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.utils.MyJobService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotificationActivity extends AppCompatActivity {

    EditText editTextSearch;
    CheckBox checkboxTechnology, checkboxScience, checkboxSports, checkboxFood, checkboxTravel, checkboxWorld;
    Switch notificationSwitchEnable;
    Boolean isNotificationEnabled;
    public String mQueryNotif;
    public String mFilterNotif;

    SearchBrain searchBrain;
    List<String> isCheckBoxList = new ArrayList<>();
    Gson gson = new Gson();

    public static final String KEY_QUERY_BUNDLE = "query_bundle";
    public static final String KEY_FILTER_BUNDLE = "filter_bundle";
    public static final String KEY_QUERY = "mQuery";
    public static final String KEY_CHECKBOX_LIST = "checkbox_list";
    public static final String KEY_NOTIFICATION_ENABLE = "isNotificationChecked";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.configureToolbar();

        editTextSearch = findViewById(R.id.text_input_layout);
        checkboxTechnology = findViewById(R.id.checkBox_technology);
        checkboxScience = findViewById(R.id.checkBox_science);
        checkboxSports = findViewById(R.id.checkBox_sports);
        checkboxFood = findViewById(R.id.checkBox_food);
        checkboxTravel = findViewById(R.id.checkBox_travel);
        checkboxWorld = findViewById(R.id.checkBox_world);
        notificationSwitchEnable = findViewById(R.id.notification_switch);

        isNotificationEnabled = getPreferences(MODE_PRIVATE).getBoolean(KEY_NOTIFICATION_ENABLE, false);

        isNotificationEnableInMemory();

        notificationSwitchEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {

                fillCheckboxList();
                //Convert an EditText in String
                queryToEditTextSearch();

                if (!isCheckBoxList.isEmpty() && !TextUtils.isEmpty(mQueryNotif)) {

                    searchBrain = new SearchBrain();
                    mFilterNotif = searchBrain.getLucene(isCheckBoxList);


                    if (bChecked) {
                        Toast.makeText(NotificationActivity.this, "Notifications actives", Toast.LENGTH_SHORT).show();
                        sharedPreferencesActions();
                        checkCheckboxIfListContainsTheirName();
                        notificationActionIfEnabled();
                    } else {
                        Toast.makeText(NotificationActivity.this, "Désactivation des notifications", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                        preferences.edit().putBoolean(KEY_NOTIFICATION_ENABLE, false).apply();
                        preferences.edit().putString(KEY_QUERY, mQueryNotif).apply();
                        preferences.edit().putString(KEY_CHECKBOX_LIST, gson.toJson(isCheckBoxList)).apply();
                        notificationActionIfIsNotEnabled();
                    }

                } else if (TextUtils.isEmpty(mQueryNotif)) {
                    editTextSearch.setError("Rentrez au moins un mot clé");
                    notificationSwitchEnable.setChecked(false);

                } else {
                    Toast.makeText(NotificationActivity.this, "Cocher au moins une Checkbox", Toast.LENGTH_SHORT).show();
                    notificationSwitchEnable.setChecked(false);

                }
            }
        });


        editTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable arg0) {

                if (editTextSearch.getText().toString().isEmpty()) {
                    editTextSearch.setError("Rentrez au moins un mot clé");
                    notificationSwitchEnable.setChecked(false);
                    sharedPreferencesActions();
                } else {
                    queryToEditTextSearch();
                    sharedPreferencesActions();
                }

            }
        });

        checkboxTechnology.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isNotificationEnabled) {
                    actionsOnChangements();
                }
            }
        });

        checkboxFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isNotificationEnabled) {
                    actionsOnChangements();
                }
            }
        });

        checkboxScience.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isNotificationEnabled) {
                    actionsOnChangements();
                }
            }
        });

        checkboxSports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isNotificationEnabled) {
                    actionsOnChangements();
                }
            }
        });

        checkboxTravel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isNotificationEnabled) {
                    actionsOnChangements();
                }
            }
        });

        checkboxWorld.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isNotificationEnabled) {
                    actionsOnChangements();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void isNotificationEnableInMemory() {
        if (isNotificationEnabled) {

            notificationSwitchEnable.setChecked(true);
            editTextSearch.setText(getPreferences(MODE_PRIVATE).getString(KEY_QUERY, null));
            checkCheckboxIfListContainsTheirName();


        } else {
            notificationSwitchEnable.setChecked(false);
            editTextSearch.setText(getPreferences(MODE_PRIVATE).getString(KEY_QUERY, null));
            checkCheckboxIfListContainsTheirName();


        }
    }

    public void sharedPreferencesActions() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putBoolean(KEY_NOTIFICATION_ENABLE, true).apply();
        preferences.edit().putString(KEY_QUERY, mQueryNotif).apply();
        preferences.edit().putString(KEY_CHECKBOX_LIST, gson.toJson(isCheckBoxList)).apply();
    }

    public void queryToEditTextSearch(){
        mQueryNotif = editTextSearch.getText().toString();
    }

    public void toastMessage(){
        Toast.makeText(NotificationActivity.this, "Modifications enregistrées", Toast.LENGTH_SHORT).show();
    }

    private void actionsOnChangements(){
        queryToEditTextSearch();
        fillCheckboxList();
        sharedPreferencesActions();
        toastMessage();
    }

    public void notificationActionIfEnabled() {

        fillCheckboxList();
        //Convert an EditText in String
        queryToEditTextSearch();

        if (!isCheckBoxList.isEmpty() && !TextUtils.isEmpty(mQueryNotif)) {

            searchBrain = new SearchBrain();
            mFilterNotif = searchBrain.getLucene(isCheckBoxList);
        }


        scheduleJob(this);

    }

    public void notificationActionIfIsNotEnabled() {
        stopJobScheduler(this);

    }

    public List<String> fillCheckboxList() {

        //Delete all elements of the List before to verify is checkbox are checked.
        isCheckBoxList.clear();

        // Check which checkbox was clicked
        if (checkboxTechnology.isChecked()) isCheckBoxList.add("Technology");
        if (checkboxScience.isChecked()) isCheckBoxList.add("Science");
        if (checkboxSports.isChecked()) isCheckBoxList.add("Sports");
        if (checkboxFood.isChecked()) isCheckBoxList.add("Food");
        if (checkboxTravel.isChecked()) isCheckBoxList.add("Travel");
        if (checkboxWorld.isChecked()) isCheckBoxList.add("World");

       return isCheckBoxList;
    }

    public void checkCheckboxIfListContainsTheirName() {

        String json = getPreferences(MODE_PRIVATE).getString(KEY_CHECKBOX_LIST, null);

        // List<String> isCheckboxListMemories = null;
        if (json != null)
            isCheckBoxList = gson.fromJson(json, new TypeToken<List<String>>() {
            }.getType());

        // Check checkbox if they are in the list
        if (isCheckBoxList.contains("Technology")) checkboxTechnology.setChecked(true);
        if (isCheckBoxList.contains("Science")) checkboxScience.setChecked(true);
        if (isCheckBoxList.contains("Sports")) checkboxSports.setChecked(true);
        if (isCheckBoxList.contains("Food")) checkboxFood.setChecked(true);
        if (isCheckBoxList.contains("Travel")) checkboxTravel.setChecked(true);
        if (isCheckBoxList.contains("World")) checkboxWorld.setChecked(true);

    }


    //Start service (job) from the JobScheduler
    public void scheduleJob(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            long flexMillis = 60 * 24 * 1000; // le temps entre chaque réquete (toutes les 24 heures)

            ComponentName serviceComponent = new ComponentName(context, MyJobService.class);
            JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
            builder.setRequiresCharging(false);
            builder.setPeriodic(flexMillis, flexMillis);
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
            PersistableBundle bundlePersistable = new PersistableBundle();
            bundlePersistable.putString(KEY_QUERY_BUNDLE, mQueryNotif);
            bundlePersistable.putString(KEY_FILTER_BUNDLE, mFilterNotif);
            builder.setExtras(bundlePersistable);

            firstScheduleJob(builder.build());

        }

    }


    // Stop service (job) from the JobScheduler
    private static void stopJobScheduler(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        assert jobScheduler != null;
        jobScheduler.cancel(0);
    }

    public JobScheduler getJobScheduler(Context context){
        return (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
    }

    public void firstScheduleJob(JobInfo jobInfo){

        getJobScheduler(this).schedule(jobInfo);

    }

}
