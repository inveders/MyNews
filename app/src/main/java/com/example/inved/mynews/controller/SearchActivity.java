package com.example.inved.mynews.controller;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.inved.mynews.R;
import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.models.ResultModel;
import com.example.inved.mynews.searchapi.Doc;
import com.example.inved.mynews.searchapi.SearchResult;
import com.example.inved.mynews.utils.MyAsyncTaskLoaderSearch;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class SearchActivity extends AppCompatActivity {

    public enum WhatDatePickerTyped { //C'est un type
        BEGIN,END
    }

    @BindView(R.id.text_input_layout)
    EditText editTextSearch;
    @BindView(R.id.button_Search)
    Button buttonSearch;
    @BindView(R.id.checkBox_technology)
    CheckBox checkboxTechnology;
    @BindView(R.id.checkBox_science)
    CheckBox checkboxScience;
    @BindView(R.id.checkBox_sports)
    CheckBox checkboxSports;
    @BindView(R.id.checkBox_food)
    CheckBox checkboxFood;
    @BindView(R.id.checkBox_travel)
    CheckBox checkboxTravel;
    @BindView(R.id.checkBox_world)
    CheckBox checkboxWorld;
    @BindView(R.id.begin_date_select)
    TextView mDisplayBeginDate;
    @BindView(R.id.end_date_select)
    TextView mDisplayEndDate;


    String TAG_DATE_PICKER ="datePicker";
    String TAG_BEGIN ="BEGIN";

    SearchBrain searchBrain;
    String mQuery;
    String mFilter;
    String mBeginDate;
    String mEndDate;
    List<String> isCheckBoxList= new ArrayList<>();
    ResultModel resultModel;


    public static final String KEY="KEY_DIALOG" ;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.configureToolbar();

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

            liveDataObservers(mQuery,mFilter,mBeginDate,mEndDate);
        });

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

    private void liveDataObservers(String mQuery, String mFilter, String mBeginDate,String mEndDate) {

        resultModel.getAllSearchResults(mQuery,mFilter,mBeginDate,mEndDate).observe(this, new Observer<List<Doc>>() {
            @Override
            public void onChanged(@Nullable List<Doc> searchResults) {

                //Update the data to adapter
                mRecyclerViewSearchAdapter.setData(searchResults);
                //Update to the UI with latest data
                mRecyclerViewSearchAdapter.notifyDataSetChanged();

                Log.d("AbsTimeFragment:", "Data has updated");
            }
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
