package com.example.inved.mynews.controller;



import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.inved.mynews.searchapi.SearchResult;
import com.example.inved.mynews.utils.MyAsyncTaskLoaderSearch;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

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
    RecyclerViewSearchAdapter mRecyclerViewSearchAdapter;
    TextView mDisplayBeginDate;
    TextView mDisplayEndDate;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    public static final String KEY="KEY_DIALOG" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        RecyclerView recyclerView = findViewById(R.id.search_recycler_view);
        mRecyclerViewSearchAdapter = new RecyclerViewSearchAdapter();
        recyclerView.setAdapter(mRecyclerViewSearchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editTextSearch = findViewById(R.id.text_input);
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

        //In this part of the code, we show a Toast when we click on the search button
        buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                fillCheckboxList();
                //Convert an EditText in String
                mQuery = editTextSearch.getText().toString();


                if(!isCheckBoxList.isEmpty() && !TextUtils.isEmpty(mQuery)) {
                    Log.d("DEBAGa", "on lance la recherche");
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
        Log.d("DEBAGa", "onLoadFinished "+data);
        if(data!=null && data.response!=null && data.response.docs!=null){
            Log.d("DEBAGa", "Nombre de résultat "+data.response.docs.size());
            mRecyclerViewSearchAdapter.setData(data.response.docs);
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
