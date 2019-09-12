
package com.example.inved.mynews.topstoriesapi;

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
    private List<Multimedium> multimedia;

    /**
     * Section used for Most popular in more than Top stories
     */

    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    public String getImageUrl() {

        if (CollectionUtils.isEmpty(media)) {

            for (int i = 0; i < multimedia.size(); i++) {
                String url = multimedia.get(i).getUrl(); /**Permet de gagner de la vitesse en gaspillant un tout petit peu de mémoire en plus(4 bytes)*/
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
