package com.example.inved.mynews.controller.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.inved.mynews.R;
import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.controller.fragments.DatePickerFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    public static final String KEY_DATA_QUERY = "QUERY";
    public static final String KEY_DATA_FILTER = "FILTER";
    public static final String KEY_DATA_BEGIN_DATE = "BEGIN_DATE";
    public static final String KEY_DATA_END_DATE = "END_DATE";
    public static final String KEY_DATA_CHECKBOX = "CHECKBOX";

    public enum WhatDatePickerTyped { //C'est un type
        BEGIN, END
    }


    EditText editTextSearch;
    Button buttonSearch;
    CheckBox checkboxTechnology;
    CheckBox checkboxScience;
    CheckBox checkboxSports;
    CheckBox checkboxFood;
    CheckBox checkboxTravel;
    CheckBox checkboxWorld;
    TextView mDisplayBeginDate;
    TextView mDisplayEndDate;


    String TAG_DATE_PICKER = "datePicker";
    String TAG_BEGIN = "BEGIN";

    SearchBrain searchBrain;
    String mQuery;
    String mFilter;
    String mBeginDate;
    String mEndDate;
    String mBeginDateNormalFormat;
    String mEndDateNormalFormat;
    List<String> isCheckBoxList = new ArrayList<>();
    Gson gson = new Gson();
    String sharedPreferencesTest;


    public static final String KEY = "KEY_DIALOG";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.configureToolbar();

        editTextSearch = findViewById(R.id.text_input_layout);
        checkboxTechnology = findViewById(R.id.checkBox_technology);
        checkboxScience = findViewById(R.id.checkBox_science);
        checkboxSports = findViewById(R.id.checkBox_sports);
        checkboxFood = findViewById(R.id.checkBox_food);
        checkboxTravel = findViewById(R.id.checkBox_travel);
        checkboxWorld = findViewById(R.id.checkBox_world);
        buttonSearch = findViewById(R.id.button_Search);
        mDisplayBeginDate = findViewById(R.id.begin_date_select);
        mDisplayEndDate = findViewById(R.id.end_date_select);

        sharedPreferencesTest = getPreferences(MODE_PRIVATE).getString(KEY_DATA_QUERY, null);
        if (sharedPreferencesTest != null) {
            searchInMemory();
        }

        mDisplayEndDate.setOnClickListener(view -> showDatePickerDialog(WhatDatePickerTyped.END));

        mDisplayBeginDate.setOnClickListener(view -> showDatePickerDialog(WhatDatePickerTyped.BEGIN));

        //In this part of the code, we show a Toast when we click on the activity_search button
        buttonSearch.setOnClickListener(view -> {

            isCheckBoxList = fillCheckboxList();
            //Convert an EditText in String
            mQuery = editTextSearch.getText().toString();


            if (!isCheckBoxList.isEmpty() && !TextUtils.isEmpty(mQuery)) {

                searchBrain = new SearchBrain();
                mFilter = searchBrain.getLucene(isCheckBoxList);
                this.startActivitySearchResult();

                Toast.makeText(SearchActivity.this, getString(R.string.search_in_progress), Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(mQuery)) {
                editTextSearch.setError(getString(R.string.enter_key_word));
            } else {
                Toast.makeText(SearchActivity.this, getString(R.string.no_checkbox_checked), Toast.LENGTH_SHORT).show();
            }


        });

    }

    /**
     * Creation of the menu in the toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_search_activity, menu);
        return true;
    }

    /**
     * Action on the icons of Menu Item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        if (item.getItemId() == R.id.menu_activity_search_refresh) {
            refreshSearchQueries();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshSearchQueries() {

        editTextSearch.setText(null);
        mDisplayBeginDate.setText(null);
        mDisplayEndDate.setText(null);
        checkboxTechnology.setChecked(false);
        checkboxScience.setChecked(false);
        checkboxSports.setChecked(false);
        checkboxFood.setChecked(false);
        checkboxTravel.setChecked(false);
        checkboxWorld.setChecked(false);
        clearSharedPreferences();
    }


    private void startActivitySearchResult() {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra(KEY_DATA_QUERY, mQuery);
        intent.putExtra(KEY_DATA_FILTER, mFilter);
        intent.putExtra(KEY_DATA_BEGIN_DATE, mBeginDate);
        intent.putExtra(KEY_DATA_END_DATE, mEndDate);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putString(KEY_DATA_QUERY, mQuery).apply();
        preferences.edit().putString(KEY_DATA_FILTER, mFilter).apply();

        if (getPreferences(MODE_PRIVATE).getString(KEY_DATA_BEGIN_DATE, null) != null) {
            preferences.edit().putString(KEY_DATA_BEGIN_DATE, getPreferences(MODE_PRIVATE).getString(KEY_DATA_BEGIN_DATE, null)).apply();
        } else {
            preferences.edit().putString(KEY_DATA_BEGIN_DATE, mBeginDateNormalFormat).apply();
        }

        if (getPreferences(MODE_PRIVATE).getString(KEY_DATA_END_DATE, null) != null) {
            preferences.edit().putString(KEY_DATA_END_DATE, getPreferences(MODE_PRIVATE).getString(KEY_DATA_END_DATE, null)).apply();
        } else {
            preferences.edit().putString(KEY_DATA_END_DATE, mEndDateNormalFormat).apply();
        }

        preferences.edit().putString(KEY_DATA_CHECKBOX, gson.toJson(isCheckBoxList)).apply();
        startActivity(intent);

    }

    private void searchInMemory() {

        editTextSearch.setText(getPreferences(MODE_PRIVATE).getString(KEY_DATA_QUERY, null));
        mDisplayBeginDate.setText(getPreferences(MODE_PRIVATE).getString(KEY_DATA_BEGIN_DATE, null));
        mDisplayEndDate.setText(getPreferences(MODE_PRIVATE).getString(KEY_DATA_END_DATE, null));
        checkCheckboxIfListContainsTheirName();


    }

    private void clearSharedPreferences() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putString(KEY_DATA_QUERY, null).apply();
        preferences.edit().putString(KEY_DATA_BEGIN_DATE, null).apply();
        preferences.edit().putString(KEY_DATA_END_DATE, null).apply();
    }

    public void checkCheckboxIfListContainsTheirName() {

        String json = getPreferences(MODE_PRIVATE).getString(KEY_DATA_CHECKBOX, null);

        // List<String> isCheckboxListMemories = null;
        if (json != null)
            isCheckBoxList = gson.fromJson(json, new TypeToken<List<String>>() {
            }.getType());

        // Check checkbox if they are in the list
        if (isCheckBoxList.contains(getString(R.string.CheckboxTechnology)))
            checkboxTechnology.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxScience)))
            checkboxScience.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxSports)))
            checkboxSports.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxFood)))
            checkboxFood.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxTravel)))
            checkboxTravel.setChecked(true);
        if (isCheckBoxList.contains(getString(R.string.CheckboxWorld)))
            checkboxWorld.setChecked(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.Search));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public List<String> fillCheckboxList() {

        //Delete all elements of the List before to verify is checkbox are checked.
        isCheckBoxList.clear();

        // Check which checkbox was clicked
        if (checkboxTechnology.isChecked())
            isCheckBoxList.add(getString(R.string.CheckboxTechnology));
        if (checkboxScience.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxScience));
        if (checkboxSports.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxSports));
        if (checkboxFood.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxFood));
        if (checkboxTravel.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxTravel));
        if (checkboxWorld.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxWorld));

        return isCheckBoxList;
    }

    public void onCheckboxClicked(View view) {

        ((CheckBox) view).setOnCheckedChangeListener((compoundButton, b) -> {

        });

    }


    public void showDatePickerDialog(WhatDatePickerTyped whatDatePickerTyped) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, whatDatePickerTyped);
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), TAG_DATE_PICKER);

    }

    public void onDatePicked(LocalDateTime localDateTime, WhatDatePickerTyped whatDatePickerTyped) {

        DateTimeFormatter searchDisplayDateFormat = DateTimeFormat.forPattern(getString(R.string.dateFormatOne));
        String stringDisplayDateFormat = localDateTime.toString(searchDisplayDateFormat);

        DateTimeFormatter retrofitFormat = DateTimeFormat.forPattern(getString(R.string.dateFormatTwo));
        String stringRetrofitDateFormat = localDateTime.toString(retrofitFormat);


        if (whatDatePickerTyped.toString().equals(TAG_BEGIN)) {
            mDisplayBeginDate.setText(stringDisplayDateFormat);
            mBeginDateNormalFormat = stringDisplayDateFormat;
            mBeginDate = stringRetrofitDateFormat;
        } else {
            mDisplayEndDate.setText(stringDisplayDateFormat);
            mEndDateNormalFormat = stringDisplayDateFormat;
            mEndDate = stringRetrofitDateFormat;
        }

    }


}
