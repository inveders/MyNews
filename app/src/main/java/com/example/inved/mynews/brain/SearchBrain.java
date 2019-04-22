package com.example.inved.mynews.brain;

import android.util.Log;

import java.util.List;

public class SearchBrain {

    public SearchBrain() {

    }

    /**
     * Convert a String in Lucene Syntax - Test
     */
    public String getLucene(List<String> userInput) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("section_name:(");
        for (int i = 0; i < userInput.size(); i++) {
            stringBuilder.append("\"")
                    .append(userInput.get(i))
                    .append("\"");
        }
        stringBuilder.append(")");
//        Log.d("DEBAGa", stringBuilder.toString());
        return stringBuilder.toString();
    }


}
