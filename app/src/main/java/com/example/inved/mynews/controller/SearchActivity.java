package com.example.inved.mynews.controller;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inved.mynews.R;
import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.searchapi.SearchResult;
import com.example.inved.mynews.utils.MyAsyncTaskLoaderSearch;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<SearchResult>{

    EditText editTextSearch;
    CheckBox checkboxTechnology,checkboxScience,checkboxSports,checkboxFood,checkboxTravel,checkboxWorld;
    Button buttonSearch;
    SearchBrain searchBrain;
    String userInput, mQuery, mFilter;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        editTextSearch = findViewById(R.id.text_input);
        checkboxTechnology = findViewById(R.id.checkBox_technology);
        checkboxScience = findViewById(R.id.checkBox_science);
        checkboxSports = findViewById(R.id.checkBox_sports);
        checkboxFood = findViewById(R.id.checkBox_food);
        checkboxTravel = findViewById(R.id.checkBox_travel);
        checkboxWorld = findViewById(R.id.checkBox_world);
        buttonSearch = findViewById(R.id.button_Search);



        //In this part of the code, we show a Toast when we click on the search button
        buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //if(isNullOrBlank(/*A CONFIGURER*/)
                searchBrain = new SearchBrain();
                mQuery = searchBrain.convertEditTextToString(editTextSearch); //to have the query
                position = onCheckboxClicked(view);
                Log.d("DEBAGO","Search2 "+position);
                mFilter = searchBrain.getFilter(position); //A COMPLETER PAR LA SUITE

             //   SearchActivity.this.initloader


               // Toast.makeText(SearchActivity.this,userInput,Toast.LENGTH_SHORT).show();
                Log.d("DEBAGO","Search3");
                Toast.makeText(SearchActivity.this,"ok",Toast.LENGTH_SHORT).show();
            }
        });


    }

    public int onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_technology:
                if (checked) return 3;
                break;
            case R.id.checkBox_science:
                if (checked) return 4;
                break;
            case R.id.checkBox_sports:
                if (checked) return 5;
                break;
            case R.id.checkBox_food:
                if (checked) return 6;
                break;
            case R.id.checkBox_travel:
                if (checked) return 7;
                break;
            case R.id.checkBox_world:
                if (checked) return 8;
                break;
            // TODO: Veggie sandwich
        }
        return 6;
    }

    @Override
    public Loader<SearchResult> onCreateLoader(int i, Bundle bundle) {
        return new MyAsyncTaskLoaderSearch(this,mQuery,mFilter);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<SearchResult> loader, SearchResult data) {

    }

    @Override
    public void onLoaderReset(Loader<SearchResult> loader) {

    }

   /* private static boolean isNullOrBlank(String s)
    {
        return (s != null && !s.trim().equals(""));
    }*/






}
