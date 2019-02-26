
package com.example.inved.mynews.searchapi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Byline {

    @SerializedName("original")
    @Expose
    public String original;
    @SerializedName("person")
    @Expose
    public List<Person> person = null;
    @SerializedName("organization")
    @Expose
    public Object organization;

}
