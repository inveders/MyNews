
package com.example.inved.mynews.topstoriesapi;

import java.util.List;

import com.example.inved.mynews.mostpopularapi.Medium;
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
    @SerializedName("thumbnail_standard")
    @Expose
    public String thumbnailStandard;
    @SerializedName("short_url")
    @Expose
    public String shortUrl;

    @SerializedName("published_date")
    @Expose
    public String publishedDate;

    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> multimedia = null;
    @SerializedName("related_urls")
    @Expose
    public List<RelatedUrl> relatedUrls = null;


    /**Section used for Most popular in more than Top stories*/


    @SerializedName("count_type")
    @Expose
    private String countType;

    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }


}
