
package com.example.inved.mynews.model.topstories;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NyTimesTopStories {

    @SerializedName("results")
    @Expose
    public List<Result> results = null;

}
