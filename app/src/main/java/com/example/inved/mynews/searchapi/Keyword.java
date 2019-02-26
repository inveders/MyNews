
package com.example.inved.mynews.searchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("value")
    @Expose
    public String value;
    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("major")
    @Expose
    public String major;

}
