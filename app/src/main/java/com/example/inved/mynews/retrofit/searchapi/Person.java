
package com.example.inved.mynews.retrofit.searchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("middlename")
    @Expose
    public Object middlename;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("qualifier")
    @Expose
    public Object qualifier;
    @SerializedName("title")
    @Expose
    public Object title;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("organization")
    @Expose
    public String organization;
    @SerializedName("rank")
    @Expose
    public Integer rank;

}
