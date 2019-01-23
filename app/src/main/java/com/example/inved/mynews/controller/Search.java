package com.example.inved.mynews.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.inved.mynews.R;

import static android.content.ContentValues.TAG;

public class Search {

    private CheckBox checkboxTechnology, checkboxScience, checkboxSports, checkboxFood, checkboxTravel, checkboxWorld ;

    Button buttonSearch;

    public void IsCheckboxChecked(){

    }

    public void addition(int pA, int pB){
        int a = pA;
        int b = pB;
        int result = a + b;
    }

  /*  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search, container, false);

        checkboxTechnology = v.findViewById(R.id.checkBox_technology);
        checkboxScience = v.findViewById(R.id.checkBox_science);
        buttonSearch = v.findViewById(R.id.button_Search);


        checkboxTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxTechnology.isChecked(); //yourCheckBox.isChecked() is the method to know if the checkBox is checked
                Log.d(TAG, "onClick: yourCheckBox = ");

                if (((CheckBox) v).isChecked()) {
                    //Toast.makeText(SearchTest.this, "Checked", Toast.LENGTH_LONG).show();
                }
            });
        }

    }*/
}
