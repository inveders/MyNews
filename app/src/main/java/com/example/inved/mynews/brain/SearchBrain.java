package com.example.inved.mynews.brain;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.inved.mynews.Themes;

public class SearchBrain {

    public SearchBrain() {

    }

    /**Convert an EditText in String*/
    public String convertEditTextToString(EditText editTextToConvert){
        String userInput =editTextToConvert.getText().toString();
        return getLucene(userInput);
    }

    /**Convert a String in Lucene Syntax - Test*/
    public String getLucene(String userInput){

        return String.format("title:\"%1$s\" OR body:\"%1$s\"",userInput);
    }

    /**To have an checkbox and convert it to filter*/
    public String getFilter(int mPosition){


        switch (Themes.values()[mPosition-1].getTitle())

        {
            case "TECHNOLOGY" : return "Technology";
            case "SCIENCE" : return "Science";
            case "SPORTS" : return "Sports";
            case "FOOD" : return "Food";
            case "TRAVEL" : return "Travel";
            case "WORLD" : return "World";
            default: return "Technology";
        }

    }

    /**To verify is an checkbox is checked*/


}
