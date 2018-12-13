
package com.example.inved.mynews.topstoriesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedUrl {

    @SerializedName("suggested_link_text")
    @Expose
    public String suggestedLinkText;
    @SerializedName("url")
    @Expose
    public String url;

}
