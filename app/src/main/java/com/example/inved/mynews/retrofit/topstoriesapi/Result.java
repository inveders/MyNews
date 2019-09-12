
package com.example.inved.mynews.retrofit.topstoriesapi;

import com.example.inved.mynews.utils.CollectionUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {


    /**
     * Sction used for top stories and over specialities: section, subsection, title, abstract, url, published date
     */

    @SerializedName("section")
    @Expose
    public String section;

    @SerializedName("subsection")
    @Expose
    public String subsection;

    @SerializedName("title")
    @Expose
    public String title;



    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("published_date")
    @Expose
    public String publishedDate;

    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia;

    /**
     * Section used for Most popular in more than Top stories
     */

    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    public Result(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public String getImageUrl() {

        if (CollectionUtils.isEmpty(media)) {

            for (int i = 0; i < multimedia.size(); i++) {

                if (url != null) {
                    return url;
                }
            }

        } else {

            for (int i = 0; i < media.size(); i++) {
                List<MediaMetadatum> url1 = media.get(i).getMediaMetadata();
                for (int j = 0; j < url1.size(); j++) {
                    String url = url1.get(j).getUrl();
                    if (url != null) {
                        return url;
                    }
                }

            }

        }

        return null;
    }

}
