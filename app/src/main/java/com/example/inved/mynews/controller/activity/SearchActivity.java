package com.example.inved.mynews.controller.activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    public static final String KEY_DATA_QUERY="QUERY" ;
    public static final String KEY_DATA_FILTER="FILTER" ;
    public static final String KEY_DATA_BEGIN_DATE="BEGIN_DATE" ;
    public static final String KEY_DATA_END_DATE="END_DATE" ;

    public enum WhatDatePickerTyped { //C'est un type
        BEGIN,END
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


    String TAG_DATE_PICKER ="datePicker";
    String TAG_BEGIN ="BEGIN";

    SearchBrain searchBrain;
    String mQuery;
    String mFilter;
    String mBeginDate;
    String mEndDate;
    List<String> isCheckBoxList= new ArrayList<>();



    public static final String KEY="KEY_DIALOG" ;


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

        mDisplayEndDate.setOnClickListener(view -> showDatePickerDialog(WhatDatePickerTyped.END));

        mDisplayBeginDate.setOnClickListener(view -> showDatePickerDialog(WhatDatePickerTyped.BEGIN));

        //In this part of the code, we show a Toast when we click on the activity_search button
        buttonSearch.setOnClickListener(view -> {

            isCheckBoxList= fillCheckboxList();
            //Convert an EditText in String
            mQuery = editTextSearch.getText().toString();


            if(!isCheckBoxList.isEmpty() && !TextUtils.isEmpty(mQuery)) {

                searchBrain = new SearchBrain();
                mFilter = searchBrain.getLucene(isCheckBoxList);

                Toast.makeText(SearchActivity.this, getString(R.string.search_in_progress), Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(mQuery)) {
                editTextSearch.setError(getString(R.string.enter_key_word));
            }
            else {
                Toast.makeText(SearchActivity.this, getString(R.string.no_checkbox_checked), Toast.LENGTH_SHORT).show();
            }

            this.startActivitySearchResult();
        });

    }

    private void startActivitySearchResult() {
        Intent intent = new Intent (this, SearchResultActivity.class);
        intent.putExtra(KEY_DATA_QUERY,mQuery);
        intent.putExtra(KEY_DATA_FILTER,mFilter);
        intent.putExtra(KEY_DATA_BEGIN_DATE,mBeginDate);
        intent.putExtra(KEY_DATA_END_DATE,mEndDate);
        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar(){
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
        if (checkboxTechnology.isChecked()) isCheckBoxList.add(getString(R.string.CheckboxTechnology));
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
        bundle.putSerializable(KEY,whatDatePickerTyped);
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), TAG_DATE_PICKER);

    }

    public void onDatePicked (LocalDateTime localDateTime, WhatDatePickerTyped whatDatePickerTyped){

        DateTimeFormatter searchDisplayDateFormat = DateTimeFormat.forPattern(getString(R.string.dateFormatOne));
        String stringDisplayDateFormat = localDateTime.toString(searchDisplayDateFormat);

        DateTimeFormatter retrofitFormat = DateTimeFormat.forPattern(getString(R.string.dateFormatTwo));
        String stringRetrofitDateFormat = localDateTime.toString(retrofitFormat);


        if (whatDatePickerTyped.toString().equals(TAG_BEGIN)){
            mDisplayBeginDate.setText(stringDisplayDateFormat);
            mBeginDate = stringRetrofitDateFormat;
        }
        else {
            mDisplayEndDate.setText(stringDisplayDateFormat);
            mEndDate = stringRetrofitDateFormat;
        }

    }


}
