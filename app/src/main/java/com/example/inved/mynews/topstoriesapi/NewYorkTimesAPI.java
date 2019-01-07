
package com.example.inved.mynews.topstoriesapi;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewYorkTimesAPI {

    @SerializedName("results")
    @Expose
    public List<Result> results = null;

}
