
package com.example.inved.mynews.retrofit.searchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response {

    @SerializedName("docs")
    @Expose
    public ArrayList<Doc> docs = null;
    @SerializedName("meta")
    @Expose
    public Meta meta;

}
