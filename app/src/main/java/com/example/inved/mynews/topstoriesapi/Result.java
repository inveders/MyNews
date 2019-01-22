
package com.example.inved.mynews.topstoriesapi;

import android.util.Log;

import com.example.inved.mynews.controller.AbsNyTimesFragment;
import com.example.inved.mynews.controller.GeneralPageFragment;
import com.example.inved.mynews.controller.MostPopularPageFragment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    private List<Multimedium> multimedia;

    /**Section used for Most popular in more than Top stories*/

    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    /*private AbsNyTimesFragment valuePageFragment = new GeneralPageFragment();

    MostPopularPageFragment valueMostPopularPageFragment = new MostPopularPageFragment();
    private int mostPopularOrNot1 ;
    private int mostPopularOrNot2 ;*/

//FAIRE UNE BOUCLE ICI

    public String getImageUrl(){
     /*   Log.d("DEBAGA", "on est dans getImageURL");
        mostPopularOrNot1 = valuePageFragment.getValue();
        mostPopularOrNot2 = valueMostPopularPageFragment.getValue();
        Log.d("DEBAGA", "0e boucle mostPopularOrNot1 "+mostPopularOrNot1+" et mostPopularOrNot2 "+mostPopularOrNot2);*/
     /*   if(valuePageFragment.isMostPopular()){
            Log.d("DEBAGA", "ismostpopular "+valuePageFragment.isMostPopular());
            return multimedia.get(1).getUrlMultimedium();
        }
        else {
            return media.get(1).getMediaMetadata().get(1).getUrlMediaMetadatum();
        }*/
       // return multimedia.get(1).getUrlMultimedium();
       // Log.d("DEBAGA", "on est dans getImageURL"+media.get(1).getMediaMetadata().get(1).getUrl());
        return "https://cdn.static.nicematin.com/media/npo/mobile_xlarge/2018/02/picasso-1-e1518944311718.jpg";


    }

}
