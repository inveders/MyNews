
package com.example.inved.mynews.topstoriesapi;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    /**Sction used for top stories and over specialities: section, subsection, title, abstract, url, published date*/

    @SerializedName("section")
    @Expose
    public String section;

    @SerializedName("subsection")
    @Expose
    public String subsection;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("abstract")
    @Expose
    public String _abstract;

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("published_date")
    @Expose
    public String publishedDate;

    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> multimedia = null;


    /**Section used for Most popular in more than Top stories*/

    @SerializedName("media-metadata")
    @Expose
    private List<MediaMetadatum> mediaMetadata = null;
}
