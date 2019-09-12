package com.example.inved.mynews.notifications;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.inved.mynews.R;
import com.example.inved.mynews.brain.SearchBrain;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class NotificationActivity extends AppCompatActivity {

    EditText editTextSearch;
    CheckBox checkboxTechnology;
    CheckBox checkboxScience;
    CheckBox checkboxSports;
    CheckBox checkboxFood;
    CheckBox checkboxTravel;
    CheckBox checkboxWorld;
    Switch notificationSwitchEnable;

    private static final int TIME_IN_HOURS = 24;

    Boolean isNotificationEnabled;
    public String mQueryNotif;
    public String mFilterNotif;

    SearchBrain searchBrain;
    List<String> isCheckBoxList = new ArrayList<>();
    Gson gson = new Gson();


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

        notificationSwitchEnable.setOnCheckedChangeListener((compoundButton, bChecked) -> {

            fillCheckboxList();
            //Convert an EditText in String
            queryToEditTextSearch();

            if (!isCheckBoxList.isEmpty() && !TextUtils.isEmpty(mQueryNotif)) {

                searchBrain = new SearchBrain();
                mFilterNotif = searchBrain.getLucene(isCheckBoxList);

                if (bChecked) {
                    Toast.makeText(NotificationActivity.this, getString(R.string.notification_actives), Toast.LENGTH_SHORT).show();
                    sharedPreferencesActions();
                    checkCheckboxIfListContainsTheirName();
                    notificationActionIfEnabled();
                } else {
                    Toast.makeText(NotificationActivity.this, getString(R.string.notification_not_actives), Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                    preferences.edit().putBoolean(KEY_NOTIFICATION_ENABLE, false).apply();
                    preferences.edit().putString(KEY_QUERY, mQueryNotif).apply();
                    preferences.edit().putString(KEY_CHECKBOX_LIST, gson.toJson(isCheckBoxList)).apply();
                    cancelJob();
                }

            } else if (TextUtils.isEmpty(mQueryNotif)) {
                editTextSearch.setError(getString(R.string.enter_key_word));
                notificationSwitchEnable.setChecked(false);

            } else {
                Toast.makeText(NotificationActivity.this, getString(R.string.check_checkbox), Toast.LENGTH_SHORT).show();
                notificationSwitchEnable.setChecked(false);

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
                    editTextSearch.setError(getString(R.string.enter_key_word));
                    notificationSwitchEnable.setChecked(false);
                    sharedPreferencesActions();
                } else {
                    queryToEditTextSearch();
                    sharedPreferencesActions();
                    //launchJob();
                }

            }
        });

    }

    public void onCheckboxClicked(View view) {
        // Take the current view?

        ((CheckBox) view).setOnCheckedChangeListener((compoundButton, b) -> {

            if (isNotificationEnabled) {

                actionsOnChangements();
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.Notification));
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
        Toast.makeText(NotificationActivity.this, getString(R.string.modifications_registered), Toast.LENGTH_SHORT).show();
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

        launchJob();


    }

    public void launchJob(){

        Data data = new Data.Builder()
                .putString(MyWorkerNotification.EXTRA_QUERY, mQueryNotif)
                .putString(MyWorkerNotification.EXTRA_FILTER, mFilterNotif)
                .build();



        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                MyWorkerNotification.class, TIME_IN_HOURS,TimeUnit.HOURS)
                .setInputData(data)
                .addTag("periodic_work")
                .build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);
    }

    public void cancelJob() {


        WorkManager.getInstance(this).cancelAllWorkByTag("periodic_work");


    }

    public List<String> fillCheckboxList() {

        //Delete all elements of the List before to verify is checkbox are checked.
        isCheckBoxList.clear();

        // Check which checkbox was clicked
        if (checkboxTechnology.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxTechnology));
        if (checkboxScience.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxScience));
        if (checkboxSports.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxSports));
        if (checkboxFood.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxFood));
        if (checkboxTravel.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxTravel));
        if (checkboxWorld.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxWorld));

       return isCheckBoxList;
    }

    public void checkCheckboxIfListContainsTheirName() {

        String json = getPreferences(MODE_PRIVATE).getString(KEY_CHECKBOX_LIST, null);

        // List<String> isCheckboxListMemories = null;
        if (json != null)
            isCheckBoxList = gson.fromJson(json, new TypeToken<List<String>>() {
            }.getType());

        // Check checkbox if they are in the list
        if (isCheckBoxList.contains(getString(R.string.CheckboxTechnology))) checkboxTechnology.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxScience))) checkboxScience.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxSports))) checkboxSports.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxFood))) checkboxFood.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxTravel))) checkboxTravel.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxWorld))) checkboxWorld.setChecked(true);

    }


}
