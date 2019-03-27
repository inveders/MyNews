package com.example.inved.mynews.controller;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inved.mynews.R;
import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.searchapi.Response;
import com.example.inved.mynews.searchapi.SearchResult;
import com.example.inved.mynews.utils.MyAsyncTaskLoaderSearch;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<SearchResult>{

    enum WhatDatePickerTyped { //C'est un type
        BEGIN,END
    }

    EditText editTextSearch;
    CheckBox checkboxTechnology,checkboxScience,checkboxSports,checkboxFood,checkboxTravel,checkboxWorld;
    Button buttonSearch;
    SearchBrain searchBrain;
    String mQuery;
    String mFilter;
    String mBeginDate;
    String mEndDate;
    List<String> isCheckBoxList= new ArrayList<>();
    TextView mDisplayBeginDate;
    TextView mDisplayEndDate;
    public static final String KEY="KEY_DIALOG" ;
    public static final String KEY_LIST_DOC="KEY_LIST_DOC" ;

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

        mDisplayEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(WhatDatePickerTyped.END);
            }
        });

        mDisplayBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(WhatDatePickerTyped.BEGIN);
            }
        });

        //In this part of the code, we show a Toast when we click on the activity_search button
        buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                fillCheckboxList();
                //Convert an EditText in String
                mQuery = editTextSearch.getText().toString();


                if(!isCheckBoxList.isEmpty() && !TextUtils.isEmpty(mQuery)) {
                   // Log.d("DEBAGa", "on lance la recherche");
                    searchBrain = new SearchBrain();
                    mFilter = searchBrain.getLucene(isCheckBoxList);

                    //Launch of the asynctaskLoaderSearch
                    startAsyncTaskLoaderSearch();

                    // Toast.makeText(SearchActivity.this,userInput,Toast.LENGTH_SHORT).show();

                    Toast.makeText(SearchActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(mQuery)) {
                    editTextSearch.setError("Rentrez au moins un mot clé");
                }
                else {
                    Toast.makeText(SearchActivity.this, "Aucune Checkbox n'est cochée", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void fillCheckboxList() {

        //Delete all elements of the List before to verify is checkbox are checked.
        isCheckBoxList.clear();

        // Check which checkbox was clicked
        if (checkboxTechnology.isChecked()) isCheckBoxList.add("Technology");
        if (checkboxScience.isChecked()) isCheckBoxList.add("Science");
        if (checkboxSports.isChecked()) isCheckBoxList.add("Sports");
        if (checkboxFood.isChecked()) isCheckBoxList.add("Food");
        if (checkboxTravel.isChecked()) isCheckBoxList.add("Travel");
        if (checkboxWorld.isChecked()) isCheckBoxList.add("World");

    }

    /**Start a new AsyncTaskLoader*/
    private void startAsyncTaskLoaderSearch(){

        //LoaderManager initialization
        getSupportLoaderManager().initLoader(1,null,this);
    }

    @NonNull
    @Override
    public Loader<SearchResult> onCreateLoader(int i, Bundle bundle) {
        return new MyAsyncTaskLoaderSearch(this,mQuery,mFilter,mBeginDate,mEndDate);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<SearchResult> loader, SearchResult data) {
        if(data!=null && data.response!=null && data.response.docs!=null){
            Log.d("DEBAGa", "Nombre de résultat "+data.response.docs.size());
            Intent intent = new Intent (this, SearchResultActivity.class);
            intent.putParcelableArrayListExtra(KEY_LIST_DOC,data.response.docs);
            startActivity(intent);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<SearchResult> loader) {

    }

    public void showDatePickerDialog(WhatDatePickerTyped whatDatePickerTyped) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY,whatDatePickerTyped);
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void onDatePicked (LocalDateTime localDateTime, WhatDatePickerTyped whatDatePickerTyped){

        DateTimeFormatter searchDisplayDateFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
        String stringDisplayDateFormat = localDateTime.toString(searchDisplayDateFormat);

        DateTimeFormatter retrofitFormat = DateTimeFormat.forPattern("yyyyMMdd");
        String stringRetrofitDateFormat = localDateTime.toString(retrofitFormat);


        if (whatDatePickerTyped.toString().equals("BEGIN")){
            mDisplayBeginDate.setText(stringDisplayDateFormat);
            mBeginDate = stringRetrofitDateFormat;
        }
        else {
            mDisplayEndDate.setText(stringDisplayDateFormat);
            mEndDate = stringRetrofitDateFormat;
        }

    }


}
