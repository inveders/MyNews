package com.example.inved.mynews.brain;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.inved.mynews.Themes;

public class SearchBrain {

    public SearchBrain() {

    }

    /**Convert a String in Lucene Syntax - Test*/
    public String getLucene(String userInput){

        return String.format("title:\"%1$s\" OR body:\"%1$s\"",userInput);
    }


}
