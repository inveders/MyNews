package com.example.inved.mynews.controller;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inved.mynews.R;
import com.example.inved.mynews.Themes;
import com.example.inved.mynews.brain.SearchBrain;

public class Search extends AppCompatActivity {

    EditText editTextSearch;
    CheckBox checkboxTechnology,checkboxScience,checkboxSports,checkboxFood,checkboxTravel,checkboxWorld;
    Button buttonSearch;
    SearchBrain searchBrain;
    String userInput, result;


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
                //if(isNullOrBlank(/*METTRE UNE STRING ICI*/)
                searchBrain = new SearchBrain();
                searchBrain.convertEditTextToString(editTextSearch);
                searchBrain.getFilter(3); //A COMPLETER PAR LA SUITE


                Toast.makeText(Search.this,userInput,Toast.LENGTH_SHORT).show();
                Log.d("DEBAGO","Search3");
                Toast.makeText(Search.this,"ok",Toast.LENGTH_SHORT).show();
            }
        });


    }

   /* private static boolean isNullOrBlank(String s)
    {
        return (s != null && !s.trim().equals(""));
    }*/






}
