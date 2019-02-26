
package com.example.inved.mynews.searchapi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("docs")
    @Expose
    public List<Doc> docs = null;
    @SerializedName("meta")
    @Expose
    public Meta meta;

}
