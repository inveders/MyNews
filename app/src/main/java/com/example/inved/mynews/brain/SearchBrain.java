package com.example.inved.mynews.brain;

import android.widget.EditText;

import com.example.inved.mynews.Themes;

public class SearchBrain {

    public SearchBrain() {

    }

    /**Convert an EditText in String*/
    public void convertEditTextToString(EditText editTextToConvert){
        String userInput =editTextToConvert.getText().toString();
        getLucene(userInput);
    }

    /**Convert a String in Lucene Syntax - Test*/
    public String getLucene(String userInput){

        return String.format("title:\"%1$s\" OR body:\"%1$s\"",userInput);
    }

    /**Avoir une CheckBox en entrée et une fq en sortie*/
        public String getFilter(int mPosition){

            switch (Themes.values()[mPosition].getTitle())
            {
            case "TECHNOLOGY" : return "technology";
            case "SCIENCE" : return "science";
            case "SPORTS" : return "sport";
            case "FOOD" : return "food";
            case "TRAVEL" : return "travel";
            case "WORLD" : return "world";
            default: return "home";
            }

    }

}
