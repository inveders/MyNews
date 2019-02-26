
package com.example.inved.mynews.searchapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedium {

    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("subtype")
    @Expose
    public String subtype;
    @SerializedName("caption")
    @Expose
    public Object caption;
    @SerializedName("credit")
    @Expose
    public Object credit;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("legacy")
    @Expose
    public Legacy legacy;
    @SerializedName("subType")
    @Expose
    public String subType;
    @SerializedName("crop_name")
    @Expose
    public String cropName;

}
